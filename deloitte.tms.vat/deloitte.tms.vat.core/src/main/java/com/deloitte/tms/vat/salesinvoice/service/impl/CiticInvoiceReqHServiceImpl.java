
/**    
 * Copyright (C),Deloitte
 * @FileName: CiticInvoiceReqHServiceImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年4月10日 下午7:12:42  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.masterdata.service.CustomerService;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.base.enums.InvoiceReqTypeEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqHDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqLDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPoolInParam;
import com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author sqing
 * @create 2016年4月10日 下午7:12:42 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(CiticInvoiceReqHService.BEAN_ID)
public class CiticInvoiceReqHServiceImpl extends BaseService implements CiticInvoiceReqHService{

	
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.core.service.IService#getDao()  
	 */
	@Resource
	InvoiceReqHDao invoiceReqHDao;
	@Autowired
	InvoiceReqLDao invoiceReqLDao;
	@Autowired
	InvoiceTrxPoolService invoiceTrxPoolService;
	@Autowired
	InvoiceReqLService invoiceReqLService;
	@Autowired
	InvoiceSyncProvider invoiceSyncProvider;
	@Autowired
	CustomerService customerService;
	@Autowired
	InvoiceReqHService invoiceReqHService;
	@Override
	public IDao getDao() {
		return invoiceReqHDao;
	}
	public InvoiceReqL convertInvoiceTrxPoolToInvoiceReqL(InvoiceTrxPool pool){
		//InvoiceReqL invoiceReqL = invoiceReqLService.findInvoiceReqLByPoolId(pool.getId());
		/*if(invoiceReqL!=null){
			return invoiceReqL;
		}
		else{*/
			InvoiceReqL invoiceReqL = new InvoiceReqL();
			//invoiceReqL.setCrvatInvoiceReqHId(entity.getId());
			invoiceReqL.setLegalEntityId(pool.getLegalEntityId());
			invoiceReqL.setLegalEntityCode(pool.getLegalEntityCode());
			invoiceReqL.setLegalEntityName(pool.getLegalEntityName());
			invoiceReqL.setCrvatTrxPoolId(pool.getId());
			invoiceReqL.setTaxTrxTypeId(pool.getTaxTrxTypeId());
			invoiceReqL.setInventoryItemId(pool.getInventoryItemId());
			invoiceReqL.setInventoryItemDescripton(pool.getInventoryItemDescripton());
			invoiceReqL.setInventoryItemModels(pool.getInventoryItmeModels());
			invoiceReqL.setInventoryItemNumber(pool.getInventoryItemNumber());
			invoiceReqL.setInventoryItemQty(pool.getInventoryItemQty());
			//invoiceReqL.setVatAmount(pool.get);
			if(!AssertHelper.isOrNotEmpty_assert(pool.getExchangeRate())){
				pool.setExchangeRate(BigDecimal.ZERO);
			}
			if(!AssertHelper.isOrNotEmpty_assert(pool.getCurrencyAmount())){
				invoiceReqL.setInvoiceAmount(BigDecimal.ZERO);
			}else{
				invoiceReqL.setInvoiceAmount(pool.getCurrencyAmount());
			}
			if(!AssertHelper.isOrNotEmpty_assert(pool.getCurrencyAmountCr())){
				invoiceReqL.setAcctdAmountCr(BigDecimal.ZERO);
			}else{
				invoiceReqL.setAcctdAmountCr(pool.getCurrencyAmountCr());
			}
			if(AssertHelper.isOrNotEmpty_assert(pool.getCurrencyAmount())&&AssertHelper.isOrNotEmpty_assert(pool.getTaxRate())){
				invoiceReqL.setVatAmount(pool.getCurrencyAmount().multiply(BigDecimal.valueOf(pool.getTaxRate())));
			}else{
				invoiceReqL.setVatAmount(BigDecimal.ZERO);
			}
			invoiceReqL.setInvoiceCategory(pool.getInvoiceCategory());
			invoiceReqL.setInvoiceType(pool.getInvoiceType());
			/*invoiceReqL.setInvoiceAmount(((BigDecimal) (null!=pool.getCurrencyAmount()?pool.getCurrencyAmount():BigDecimal.valueOf(0.00))));
			invoiceReqL.setAcctdAmountCr(null!=pool.getCurrencyAmountCr()?pool.getCurrencyAmountCr():BigDecimal.valueOf(0.00));
			invoiceReqL.setVatAmount(((BigDecimal) (null!=pool.getCurrencyAmount()?pool.getCurrencyAmount():BigDecimal.valueOf(0.00)).multiply(null!=pool.getTaxRate()?BigDecimal.valueOf(pool.getTaxRate()):BigDecimal.valueOf(0.00))));*/
			return invoiceReqL;
		//}
	}
	public InvoiceReqH getInvoiceReqH(String id) {
		// TODO Auto-generated method stub
		InvoiceReqH invoiceReqH =(InvoiceReqH) invoiceReqLDao.get(InvoiceReqH.class, id);	
		return invoiceReqH;
	}
	public void saveInvoiceReqHeadAndRel(InvoiceReqH invoiceReqH) {
		// TODO Auto-generated method stub
		this.save(invoiceReqH);
		for(InvoiceReqL invoiceReqL:invoiceReqH.getInvoiceReqLs()){
			InvoiceTrxPool pool=(InvoiceTrxPool) this.get(InvoiceTrxPool.class, invoiceReqL.getCrvatTrxPoolId());
			pool.setStatus(CrvatTaxPoolStatuEnums.APPFORM_USED.getValue());
			this.save(invoiceReqL);
			invoiceTrxPoolService.update(pool);
		}
		
	}
	public void updateInvoiceReq(InvoiceReqH invoiceReqH) {
		// TODO Auto-generated method stub
		String hid = invoiceReqH.getId();
		deleteInvoiceReLByReHId(hid);
		for(InvoiceReqL invoiceReqL:invoiceReqH.getInvoiceReqLs()){
			InvoiceTrxPool pool=(InvoiceTrxPool) this.get(InvoiceTrxPool.class, invoiceReqL.getCrvatTrxPoolId());
			pool.setStatus(CrvatTaxPoolStatuEnums.APPFORM_USED.getValue());
			invoiceTrxPoolService.update(pool);
			invoiceReqHDao.save(invoiceReqL);
		}
	}
	public void deleteInvoiceReLByReHId(String id) {
		// TODO Auto-generated method stub
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("id", id);
		invoiceReqLDao.executeHqlProduce("delete from InvoiceReqL where crvatInvoiceReqHId = :id", values);
	}
	/**
	 * @author sqing
	 * @see得到页面展示所需的行表金额信息
	 */
	public InvoiceReqLInParam getAmout(String poolId,InvoiceTrxPool pool){
		Map<String,Object>params=new HashMap();
		params.put("crvatTrxPoolId", poolId);
		List<InvoiceReqL>list=invoiceReqLDao.findInvoiceReqLByParams(params);
		InvoiceReqLInParam rInParam=new InvoiceReqLInParam();
		BigDecimal amount = BigDecimal.valueOf(0);
		for (int i = 0; i < list.size(); i++) {
			amount=amount.add(list.get(i).getInvoiceAmount());
		}
//		rInParam.setUserfulAmount((pool.getCurrencyAmount().subtract(amount)).toString());
//		rInParam.setUsedAmount(amount.toString());
		rInParam.setUserfulAmount(invoiceSyncProvider.getUserfulAmountByTrxPoolId(poolId).toString());
		rInParam.setUsedAmount(invoiceSyncProvider.getUsedAmount(poolId).toString());
		return rInParam;
	}
	/**   
	 * @param map
	 * @return
	 * @throws ParseException  
	 * @see com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService#setUpHead(java.util.Map)  
	 */
	
	@Override
	public String setUpHead(Map<String, Object> map) throws ParseException {
		String trxIds = (String) map.get("rowsids");
		String trxs[] = trxIds.split(";");
		List<InvoiceTrxPool> list = new ArrayList<InvoiceTrxPool>();
		String hid="";
		String name=map.get("name").toString();
		for(int i=0;i<trxs.length;i++){
			InvoiceTrxPool invoiceTrxPool = (InvoiceTrxPool) this.get(InvoiceTrxPool.class, trxs[i]);
			if(null!=invoiceTrxPool){
				list.add(invoiceTrxPool);
			}
		}
		if(!AssertHelper.empty(map.get("id"))){
			String id = map.get("id").toString();
			InvoiceReqH invoiceReqH = this.getInvoiceReqH(id);
			List<InvoiceReqL> invoiceReqLs = new ArrayList<InvoiceReqL>();					
			for(InvoiceTrxPool invoiceTrxPool:list){
				InvoiceReqL entity=new InvoiceReqL();
				entity=this.convertInvoiceTrxPoolToInvoiceReqL(invoiceTrxPool);
				entity.setId(IdGenerator.getUUID());
				entity.setCrvatInvoiceReqHId(invoiceReqH.getId());
				entity.setStatus(map.get("status").toString());
				entity.setOrgId(map.get("orgId").toString());
				invoiceReqLs.add(entity);							
			}
			invoiceReqH.setInvoiceReqLs(invoiceReqLs);
			this.updateInvoiceReq(invoiceReqH);
			hid=invoiceReqH.getId();
		}else{
			InvoiceReqH invoiceReqH = new InvoiceReqH();
			invoiceReqH.setId(IdGenerator.getUUID());
			invoiceReqH.setCustomerId(map.get("customerId").toString());
			invoiceReqH.setCustRegistrationNumber(map.get("custRegistrationNumber").toString());
			String sequece = FlowHelper.getNextFlowNo("INVOICEREQ");
			invoiceReqH.setCrvatInvoiceReqNumber(sequece);
			invoiceReqH.setStatus(map.get("status").toString());
			invoiceReqH.setIsAllMapping("0");
			invoiceReqH.setMappingStatus("0");
			invoiceReqH.setOrgId(map.get("orgId").toString());
			invoiceReqH.setCustRegistrationCode(map.get("custRegistrationCode").toString());
			/*String dateString = map.get("invoiceReqDate").toString();
			Date date = DateUtils.parse(dateString);*/
			Date date = getDatabaseServerDate();
			invoiceReqH.setInvoiceReqDate(date);
			invoiceReqH.setReqInvoiceRange(map.get("reqInvoiceRange").toString());
			invoiceReqH.setInvoiceReqType(InvoiceReqTypeEnums.COUNTER.getValue());
			List<InvoiceReqL> invoiceReqLs = new ArrayList<InvoiceReqL>();
			for(InvoiceTrxPool invoiceTrxPool:list){
				InvoiceReqL entity=new InvoiceReqL();
				entity=convertInvoiceTrxPoolToInvoiceReqL(invoiceTrxPool);
				entity.setId(IdGenerator.getUUID());
				entity.setCrvatInvoiceReqHId(invoiceReqH.getId());
				entity.setStatus(map.get("status").toString());
				entity.setOrgId(map.get("orgId").toString());
				invoiceReqLs.add(entity);
			}
			invoiceReqH.setInvoiceReqLs(invoiceReqLs);
			this.saveInvoiceReqHeadAndRel(invoiceReqH);
			hid=invoiceReqH.getId();
		}
		//this.updateAdminStatus(name);
		return hid;
	}
	
	/**   
	 * @param pool
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService#convertInvoiceTrxPoolToInParam(com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool)  
	 */
	
	@Override
	public InvoiceTrxPoolInParam convertInvoiceTrxPoolToInParam(
			InvoiceTrxPool initiation) {
		//InvoiceTrxPoolInParam inparam=convertInvoiceTrxPoolToInParam(initiation);
		InvoiceTrxPoolInParam inparam=new InvoiceTrxPoolInParam();
		inparam.setId(initiation.getId());
		inparam.setTaxRate(null!=initiation.getTaxRate()?initiation.getTaxRate():0.00);
		inparam.setTrxAffirmId(initiation.getTrxAffirmId());
		inparam.setTrxNumber(initiation.getTrxNumber());
		InvoiceReqLInParam amountInParam=getAmout(initiation.getId(),initiation);
		inparam.setUsedAmount(amountInParam.getUsedAmount());
		inparam.setUserfulAmount(amountInParam.getUserfulAmount());
		inparam.setCrvatTrxPoolId(initiation.getId());
		inparam.setTrxDate(initiation.getTrxDate());
		inparam.setInvoiceAmount(null!=inparam.getCurrencyAmountCr()?inparam.getCurrencyAmountCr():BigDecimal.valueOf(0.00).multiply(null!=inparam.getExchangeRate()?inparam.getExchangeRate():BigDecimal.valueOf(0.00)));
		if(AssertHelper.isOrNotEmpty_assert(initiation.getOrgId())){
			BizOrgNode node=OrgCacheUtils.getNodeByOrgId(initiation.getOrgId());
			if(null!=node){
				inparam.setOrgName(node.getName());
				inparam.setOrgCode(node.getCode());	
			}
		}
		// TO DO
		String codeValue = initiation.getStatus();
		if(AssertHelper.isOrNotEmpty_assert(codeValue)){
			String valueName = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_TRX_ITEM_STATUS", codeValue); 
			inparam.setStatus(valueName);
		}
		if(AssertHelper.isOrNotEmpty_assert(initiation.getTaxTrxTypeId())){
			TmsMdTaxTrxType trxType=(TmsMdTaxTrxType) invoiceReqLService.get(TmsMdTaxTrxType.class, initiation.getTaxTrxTypeId());
			if(null!=trxType){
				inparam.setTaxTrxTypeCode(trxType.getTaxTrxTypeCode());
				inparam.setTaxTrxTypeName(trxType.getTaxTrxTypeName());
			}
		}
		if(AssertHelper.isOrNotEmpty_assert(initiation.getInventoryItemId())){
			TmsMdInventoryItems items = (TmsMdInventoryItems) this.get(TmsMdInventoryItems.class, initiation.getInventoryItemId());
			if(null!=items){
				inparam.setInventoryItemDescripton(items.getInventoryItemDescripton());
			}
		}
		if(AssertHelper.isOrNotEmpty_assert(initiation.getLegalEntityId())){
			TmsMdLegalEntity entity=(TmsMdLegalEntity) this.get(TmsMdLegalEntity.class, initiation.getLegalEntityId());
			if(null!=entity){
				inparam.setLegalEntityCode(entity.getLegalEntityCode());
				inparam.setLegalEntityName(entity.getLegalEntityName());
			}
		}
		return inparam;
	}
	
	/**   
	 * @param map
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService#findInvoiceTrxPoolByParams(java.util.Map)  
	 */
	
	@Override
	public InvoiceTrxPool findInvoiceTrxPoolByParams(Map<String, Object> map) {
		InvoiceTrxPool pool= invoiceReqHDao.findWithOutCustomer(map);
		return pool;
	}
	
	/**   
	 * @param map  
	 * @see com.deloitte.tms.vat.salesinvoice.service.CiticInvoiceReqHService#saveCustomerAndReq(java.util.Map)  
	 */
	
	@Override
	public void saveCustomerAndReq(Map<String, Object> map) {
		Customer entity=invoiceReqHService.getCustomerParam(map);
		String customerId="";
		if(!AssertHelper.isOrNotEmpty_assert(entity.getId())){
			Customer customer=new Customer();
			customer.setIsAppointInvoice("");//是否预约开票
			customer.setIsInvoiceProviding("");//是否开具发票
			//String flag=DictionaryCacheUtils.getCodeValue("BASE_IS_ENABLED", "Yes");
			customer.setEnabledFlag("1");
			customer.setCustRegistrationCode(map.get("custRegistrationCode").toString());
			customer.setCustRegistrationAddress(map.get("custRegistrationAddress").toString());
			customer.setCustRegistrationNumber(map.get("custRegistrationNumber").toString());
			customer.setCustomerName(map.get("customerName").toString());
			customer.setCustDepositBankName(map.get("custDepositBankName").toString());
			customer.setCustDepositBankAccountNum(map.get("custDepositBankAccountNum").toString());
			customer.setContactPhone(map.get("contactPhone").toString());
			customer.setCustRegistrationAddress(map.get("custRegistrationAddress").toString());
			customerService.save(customer);
			customerId=customer.getId();
		}else{
			customerId=entity.getId();
		}
		String id=map.get("id").toString();
		InvoiceTrxPool pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, id);
		InvoiceReqL invoiceReqL=this.convertInvoiceTrxPoolToInvoiceReqL(pool);
		InvoiceReqH invoiceReqH = new InvoiceReqH();
		invoiceReqH.setId(IdGenerator.getUUID());
		invoiceReqH.setCustomerId(customerId);
		invoiceReqH.setCustRegistrationNumber(map.get("custRegistrationNumber").toString());
		String sequece = FlowHelper.getNextFlowNo("INVOICEREQ");
		invoiceReqH.setCrvatInvoiceReqNumber(sequece);
		invoiceReqH.setStatus(map.get("status").toString());
		invoiceReqH.setIsAllMapping("0");
		invoiceReqH.setMappingStatus("0");
		//invoiceReqH.setOrgId(map.get("orgId").toString());
		invoiceReqH.setCustRegistrationCode(map.get("custRegistrationCode").toString());
		/*String dateString = map.get("invoiceReqDate").toString();
		Date date = DateUtils.parse(dateString);*/
		invoiceReqH.setInvoiceReqDate(new Date());
		//invoiceReqH.setReqInvoiceRange(map.get("reqInvoiceRange").toString());
		invoiceReqH.setInvoiceReqType(InvoiceReqTypeEnums.COUNTER.getValue());
		List<InvoiceReqL> invoiceReqLs = new ArrayList<InvoiceReqL>();
		invoiceReqLs.add(invoiceReqL);
		invoiceReqH.setInvoiceReqLs(invoiceReqLs);
		this.saveInvoiceReqHeadAndRel(invoiceReqH);
	}
}
