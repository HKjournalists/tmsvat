package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.service.CustomerService;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItemsInParam;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.vat.base.enums.InvoiceReqTypeEnums;
import com.deloitte.tms.vat.base.enums.InvoicingTypeEnums;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSpecialDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqP;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoiceReqPInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialService;
import com.deloitte.tms.vat.salesinvoice.service.TmsCrvatInvoiceReqPService;

@Service
public class InvoiceSpecialServiceImpl extends BaseService implements
		InvoiceSpecialService {
	
	@Resource
	CustomerService customerService;
	@Resource
	InvoiceSpecialDao invoiceSpecialDaoImpl;
	@Resource
	TmsCrvatInvoiceReqPService invoiceReqPService;
	
	@Override
	public IDao getDao() {
		return  invoiceSpecialDaoImpl;
	}

	/**
	 * 申请单数据查询
	 */

	@Override
	public DaoPage findInvoiceReqAll(Map<String, Object> map,
			Integer pageIndex, Integer pageSize) {
		if (map == null) {
			map = new HashMap();
		}
		DaoPage daoPage = this.findInvoiceReqHByParams(map, pageIndex, pageSize);
		return daoPage;
	}
	
    /**
     * 申请单数据查询
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
	@Override
	public DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null){
			params=new HashMap();
		}
		if(AssertHelper.isOrNotEmpty_assert(params.get("customerNumber"))){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("customerNumber", params.get("customerNumber"));
			List<CustomerInParam>list=customerService.findCustomerByParams(map);
			String ids="";
			if(list.size()>0){
				ids=list.get(0).getId();
			}
			params.put("customerId", ids);
		}
		DaoPage daoPage= invoiceSpecialDaoImpl.findInvoiceReqHByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceReqHToInParam((List<InvoiceReqH>) daoPage.getResult()));
		return daoPage;
	}
	
	/**
	 * 申请单数据查询
	 * @param models
	 * @return
	 */
	private List<InvoiceReqHInParam> convertInvoiceReqHToInParam(List<InvoiceReqH> models){
		List<InvoiceReqHInParam> result=new ArrayList<InvoiceReqHInParam>();
		for(InvoiceReqH initiation:models){
			InvoiceReqHInParam inparam=convertInvoiceReqHToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	
	/**
	 * 申请单数据查询结果往前台model赋值
	 * @param model
	 * @return
	 */
	public InvoiceReqHInParam convertInvoiceReqHToInParam(InvoiceReqH model){
		InvoiceReqHInParam inparam=new InvoiceReqHInParam();
		ReflectUtils.copyProperties(model, inparam);
		String codeValue = model.getCustRegistrationCode();//购方纳税识别号类型
		if(AssertHelper.isOrNotEmpty_assert(codeValue)){//
			String valueName = DictionaryCacheUtils.getCodeName("VAT_CUSTOMER_DISC_OPTION", codeValue); 
			inparam.setCustRegistrationCode(valueName);
		}if(AssertHelper.isOrNotEmpty_assert(model.getCustomer())){//客户关系
			inparam.setCustomerNumber(model.getCustomer().getCustomerNumber());//购方编码
			inparam.setCustomerName(model.getCustomer().getCustomerName());//购方名称
			inparam.setCustRegistrationAddress(model.getCustomer().getCustRegistrationAddress());//购方纳税人地址
			inparam.setCustDepositBankName(model.getCustomer().getCustDepositBankName());//购方开户银行名称
			inparam.setCustDepositBankAccountNum(model.getCustomer().getCustDepositBankAccountNum());//购方开户账号
			inparam.setContactName(model.getCustomer().getContactName());////购方纳税联系人
			inparam.setCustomerId(model.getCustomerId());
			
		}if(AssertHelper.isOrNotEmpty_assert(model.getStatus())){//状态
			inparam.setStatus(DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_APPFORM_STATUS",model.getStatus()));
			inparam.setPageStatus(model.getStatus());//页面展现的状态值
			inparam.setReqStatus(model.getStatus());//申请单状态
		}if(AssertHelper.isOrNotEmpty_assert(model.getOrgId())){//组织id
			BizOrgNode node=OrgCacheUtils.getNodeByOrgId(model.getOrgId());//查询组织信息
			if(node!=null){
				inparam.setOrgName(node.getName());//组织名称
				inparam.setOrgCode(node.getCode());	//组织code
			}
			
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getInvoiceReqType())){//申请开票类型(柜台/特殊/自动)
			String valueName = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_REQ_TYPE", model.getInvoiceReqType()); 
			inparam.setInvoiceReqType(valueName);
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getLegalEntityId())){
			TmsMdLegalEntity entity=(TmsMdLegalEntity) this.get(TmsMdLegalEntity.class, model.getLegalEntityId());
			inparam.setLegalEntityCode(entity.getLegalEntityCode());
			inparam.setLegalEntityName(entity.getLegalEntityName());
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getInvoiceReqLs())){
			List<InvoiceReqL>list=(List<InvoiceReqL>) model.getInvoiceReqLs();
			BigDecimal amount=BigDecimal.valueOf(0.00);
			for (int i = 0; i < list.size(); i++) {
				amount=amount.add(list.get(i).getInvoiceAmount());
			}
			inparam.setReqAmount(amount.toString());
			inparam.setAcctdAmountCr("0");
		}
		return inparam;
	}
    /**
     * 根据填写购方编码，购方证件号码查询
     * @param map
     * @return
     */
	@Override
	public Customer getCustomerParam(Map<String, Object> map) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from Customer where 1=1 ");
		Object custRegistrationNumber=map.get("custRegistrationNumber");
		Object customerNumber=map.get("customerNumber");
		String custRegistrationCode=map.get("custRegistrationCode").toString();
		//String code = dictionaryService.
		if(AssertHelper.isOrNotEmpty_assert(custRegistrationCode)){
			query.append(" and custRegistrationCode=:custRegistrationCode");
			values.put("custRegistrationCode", custRegistrationCode);
		}
		if(AssertHelper.isOrNotEmpty_assert(customerNumber)){
			query.append(" and customerNumber=:customerNumber");
			values.put("customerNumber", customerNumber);
		}if(AssertHelper.isOrNotEmpty_assert(custRegistrationNumber)){
			query.append(" and custRegistrationNumber=:custRegistrationNumber");
			values.put("custRegistrationNumber", custRegistrationNumber);
		}
		List<Customer>list= customerService.findBy(query, values);
		if(values.size()>0){
			if(list.size()>0){
				return list.get(0);
			}else{
				return new Customer();
			}
		}
		else{
			return new Customer();
		}
	}
    
	/**
	 * 商品及服务编码查询
	 */
	@Override
	public DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceSpecialDaoImpl.findTmsMdInventoryItemsByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdInventoryItemsToInParam((List<TmsMdInventoryItems>) daoPage.getResult()));
		return daoPage;
	}
	/**
	 * 商品及服务编码查询
	 */
	private List<TmsMdInventoryItemsInParam> convertTmsMdInventoryItemsToInParam(List<TmsMdInventoryItems> models){
		List<TmsMdInventoryItemsInParam> result=new ArrayList<TmsMdInventoryItemsInParam>();
		for(TmsMdInventoryItems initiation:models){
			TmsMdInventoryItemsInParam inparam=convertTmsMdInventoryItemsToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	/**
	 * 商品及服务编码查询
	 */
	public TmsMdInventoryItemsInParam convertTmsMdInventoryItemsToInParam(TmsMdInventoryItems model){
		TmsMdInventoryItemsInParam inparam=new TmsMdInventoryItemsInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}

	@Override
	/**
	 * 保存
	 */
	public String setUpHead(Map<String, Object> map) throws ParseException {
		String hid="";
		if(!AssertHelper.empty(map.get("id"))){//编辑
			String id = map.get("id").toString();
			InvoiceReqH invoiceReqH = (InvoiceReqH) invoiceSpecialDaoImpl.get(InvoiceReqH.class, id);
			
			JSONArray jsonArray = JSONArray.fromObject(map.get("dgrequestdetaildata"));
			List<TmsCrvatInvoiceReqP> invoiceReqPs = new ArrayList<TmsCrvatInvoiceReqP>();		
			for(int i=0;i<jsonArray.size(); i++){
				JSONObject jsonJ = jsonArray.getJSONObject(i);
				if(null!=jsonJ){
					TmsCrvatInvoiceReqP invoiceReqP = new TmsCrvatInvoiceReqP();
					invoiceReqP.setId(IdGenerator.getUUID());
					invoiceReqP.setUomCode(jsonJ.getString("uomCode"));//单位
					invoiceReqP.setInventoryItemDescripton(jsonJ.getString("inventoryItemDescripton"));//商品及服务名称
					invoiceReqP.setInventoryItemModels(jsonJ.getString("inventoryItemModels"));//规格型号
					invoiceReqP.setInventoryItemId(jsonJ.getString("id"));//商品及服务id
					invoiceReqP.setInventoryItemQty(Long.parseLong(jsonJ.getString("taxTrxTypeCode")));//数量
					invoiceReqP.setPriceOfUnit(Long.parseLong(jsonJ.getString("legalEntityName")));//单价
					invoiceReqP.setAcctdAmountCr(new BigDecimal(jsonJ.getString("legalEntityCode")));//合计金额
					invoiceReqP.setVatAmount(new BigDecimal(jsonJ.getString("inventory")));//税额
					invoiceReqP.setInvoiceAmount(new BigDecimal(jsonJ.getString("trxDate")));//净额
					
					invoiceReqPs.add(invoiceReqP);
				}
			}
			invoiceReqH.setInvoiceReqPs(invoiceReqPs);
			this.saveInvoiceReqHeadAndRel(invoiceReqH);
			hid=invoiceReqH.getId();
		}else{//新建
			InvoiceReqH invoiceReqH = new InvoiceReqH();
			invoiceReqH.setId(IdGenerator.getUUID());
			
			//根据客户编号查询客户信息
			List<Customer> listCustomer = invoiceSpecialDaoImpl.findCustomer(map.get("customerNumber").toString(),null);
			if(listCustomer.size()>0){
				Customer customer = listCustomer.get(0);
				invoiceReqH.setCustomerName(customer.getCustomerName());//购方名称
				invoiceReqH.setCustomerId(customer.getId());
				invoiceReqH.setCustRegistrationCode(customer.getCustRegistrationCode());//购方证件类型
				invoiceReqH.setCustRegistrationNumber(customer.getCustRegistrationNumber());//购方证件号码
			}
			//前端页面不去生成申请单编号，后台创建时生成
			String sequece = FlowHelper.getNextFlowNo("INVOICEREQ");
			invoiceReqH.setCrvatInvoiceReqNumber(sequece);
			invoiceReqH.setStatus(map.get("status").toString());
			invoiceReqH.setIsAllMapping("0");
			invoiceReqH.setMappingStatus("0");
			invoiceReqH.setIsReceipts(map.get("isReceipts").toString());
			invoiceReqH.setIsTax(map.get("isTax").toString());
			invoiceReqH.setOrgId(map.get("orgId").toString());
			invoiceReqH.setCustRegistrationCode(map.get("custRegistrationCode").toString());
			Date date = getDatabaseServerDate();
			invoiceReqH.setInvoiceReqDate(date);
			invoiceReqH.setReqInvoiceRange(map.get("reqInvoiceRange").toString());
			invoiceReqH.setInvoiceReqType(InvoiceReqTypeEnums.SPECIAL_NO_CONTRACT.getValue());//特殊-无合同
			
			JSONArray jsonArray = JSONArray.fromObject(map.get("dgrequestdetaildata"));
			List<TmsCrvatInvoiceReqP> invoiceReqPs = new ArrayList<TmsCrvatInvoiceReqP>();
			for(int i=0;i<jsonArray.size(); i++){
				JSONObject jsonJ = jsonArray.getJSONObject(i);
				if(null!=jsonJ){
					TmsCrvatInvoiceReqP p = new TmsCrvatInvoiceReqP();
					p.setId(IdGenerator.getUUID());
					p.setUomCode(jsonJ.getString("uomCode"));//单位
					p.setInventoryItemDescripton(jsonJ.getString("inventoryItemDescripton"));//商品及服务名称
					p.setInventoryItemModels(jsonJ.getString("inventoryItemModels"));//规格型号
					p.setInventoryItemId(jsonJ.getString("id"));//商品及服务id
					p.setInventoryItemQty(new BigDecimal(jsonJ.getString("taxTrxTypeCode")).longValue());//数量
					p.setPriceOfUnit(new BigDecimal(jsonJ.getString("legalEntityName")).longValue());//单价
					p.setAcctdAmountCr(new BigDecimal(jsonJ.getString("legalEntityCode")));//合计金额
					p.setVatAmount(new BigDecimal(jsonJ.getString("inventory")));//税额
					p.setInvoiceAmount(new BigDecimal(jsonJ.getString("trxDate")));//净额
					//开票方式，枚举值：D=明细，S=汇总
					p.setInvoicingType(InvoicingTypeEnums.DETAIL.getValue());
					
					System.out.println(jsonJ.getString("taxRate"));//税率
					
					invoiceReqPs.add(p);
				}
			}
			invoiceReqH.setInvoiceReqPs(invoiceReqPs);
			
			this.updateInvoiceReqHeadAndRel(invoiceReqH);
			hid=invoiceReqH.getId();
		}
		//this.updateAdminStatus(name);
		return hid;
	}
	
	/**
	 * 新增特殊开票头信息和发票明细信息
	 * @param invoiceReqH
	 */
	public void saveInvoiceReqHeadAndRel(InvoiceReqH invoiceReqH) {
		invoiceSpecialDaoImpl.save(invoiceReqH);
		String hid = invoiceReqH.getId();
		for(TmsCrvatInvoiceReqP invoiceReqP:invoiceReqH.getInvoiceReqPs()){
			invoiceReqP.setCrvatInvoiceReqHId(hid);
			//save it
			invoiceReqPService.save(invoiceReqP);
		}
		
	}
	
	/**
	 * 修改特殊开票头信息和发票明细信息
	 * @param invoiceReqH
	 */
	public void updateInvoiceReqHeadAndRel(InvoiceReqH invoiceReqH) {
		invoiceSpecialDaoImpl.save(invoiceReqH);
		String hid = invoiceReqH.getId();
		this.deleteInvoiceReLByReqHId(hid);
		for(TmsCrvatInvoiceReqP invoiceReqP:invoiceReqH.getInvoiceReqPs()){
			invoiceReqP.setCrvatInvoiceReqHId(hid);
			//save it
			invoiceReqPService.save(invoiceReqP);
		}
		
	}
	
	/**
	 * 根据头表ID删除发票明细
	 * @param id
	 */
	public void deleteInvoiceReLByReqHId(String id) {
		List<TmsCrvatInvoiceReqP> reqPs = invoiceReqPService.findInvoiceReLByReqHId(id);
		if(reqPs.size()>0){
			for(TmsCrvatInvoiceReqP reqP:reqPs){
				invoiceReqPService.remove(reqP);
			}
		}
	}

	@Override
	/**
	 * 删除选中的特殊发票申请单（无合同）
	 */
	public void deleteFromReqAll(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			InvoiceReqH entity=(InvoiceReqH) invoiceSpecialDaoImpl.get(InvoiceReqH.class, ids[i]);
			deleteInvoiceReLByReqHId(entity.getId());
			invoiceSpecialDaoImpl.remove(entity);
		}
	}

	@Override
	public InvoiceReqH getInvoiceReqH(String id) {
		return (InvoiceReqH) invoiceSpecialDaoImpl.get(InvoiceReqH.class, id);
	}

	public TmsCrvatInvoiceReqPInParam convertReqP2Param(TmsCrvatInvoiceReqP reqP) {
		TmsCrvatInvoiceReqPInParam inParam = new TmsCrvatInvoiceReqPInParam();
		ReflectUtils.copyProperties(reqP, inParam);
		//从商品信息获取税率数据
		TmsMdInventoryItems item = (TmsMdInventoryItems) invoiceSpecialDaoImpl.get(TmsMdInventoryItems.class, reqP.getInventoryItemId());
		if (null != item && null != item.getTaxRate()) {
			inParam.setTaxRate(item.getTaxRate());
		}
		return inParam;
	}

	@Override
	public List<TmsCrvatInvoiceReqPInParam> convertReqP2Param(
			List<TmsCrvatInvoiceReqP> reqPs) {
		List<TmsCrvatInvoiceReqPInParam> inParams = new ArrayList<TmsCrvatInvoiceReqPInParam>();
		for(TmsCrvatInvoiceReqP reqP:reqPs){
			inParams.add(convertReqP2Param(reqP));
		}
		return inParams;
	}
}
