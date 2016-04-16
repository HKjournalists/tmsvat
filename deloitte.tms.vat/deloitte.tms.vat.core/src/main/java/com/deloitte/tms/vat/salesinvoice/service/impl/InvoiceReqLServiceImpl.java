package com.deloitte.tms.vat.salesinvoice.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqLDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;
/**
 * Home object for domain model class InvoiceReqL.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Component(InvoiceReqLService.BEAN_ID)
public class InvoiceReqLServiceImpl extends BaseService implements InvoiceReqLService{
	@Resource
	InvoiceReqLDao invoiceReqLDao;
	@Autowired
	InvoiceReqHService invoiceReqHService;
	@Autowired
	InvoiceTrxPoolService invoiceTrxPoolService;
	@Resource
	InvoiceSyncProvider invoiceSyncProvider;
	@Override
	public IDao getDao() {
		return invoiceReqLDao;
	}

	@Override
	public DaoPage findInvoiceReqLByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceReqLDao.findInvoiceReqLByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceReqLToInParam((List<InvoiceReqL>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<InvoiceReqLInParam> findInvoiceReqLByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<InvoiceReqL> temp= invoiceReqLDao.findInvoiceReqLByParams(params);
		return convertInvoiceReqLToInParam(temp);
	}
	@Override
	public void saveInvoiceReqLDataListsMap(Map dataListsMap) {
		Collection<InvoiceReqLInParam> deleteList = BatchUtils.to(InvoiceReqLInParam.class).getDeleteEntities(dataListsMap);
		Collection<InvoiceReqLInParam> insertList =  BatchUtils.to(InvoiceReqLInParam.class).getInsertEntities(dataListsMap);
		Collection<InvoiceReqLInParam> updateList =  BatchUtils.to(InvoiceReqLInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(InvoiceReqLInParam inParam: updateList){
				InvoiceReqL entity=convertInvoiceReqLInParamToEntity(inParam);
				invoiceReqLDao.update(entity);
			}		
//			invoiceReqLDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(InvoiceReqLInParam inParam: insertList){
				InvoiceReqL entity=convertInvoiceReqLInParamToEntity(inParam);
				invoiceReqLDao.save(entity);
				inParam.setId(entity.getId());
			}
//			invoiceReqLDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(InvoiceReqLInParam inParam:deleteList){
				InvoiceReqL entity=new InvoiceReqL();
				entity.setId(inParam.getId());
				invoiceReqLDao.remove(entity);
			}
//			invoiceReqLDao.removeAll(deleteList);
		}
		
	}
	private List<InvoiceReqLInParam> convertInvoiceReqLToInParam(List<InvoiceReqL> models){
		List<InvoiceReqLInParam> result=new ArrayList<InvoiceReqLInParam>();
		for(InvoiceReqL initiation:models){
			InvoiceReqLInParam inparam=convertInvoiceReqLToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public InvoiceReqLInParam convertInvoiceReqLToInParam(InvoiceReqL model){
		InvoiceReqLInParam inparam=new InvoiceReqLInParam();
		InvoiceReqLInParam amountInParam;
		ReflectUtils.copyProperties(model, inparam);
		inparam.setTrxid(model.getCrvatTrxPoolId());
		InvoiceTrxPool pool;
		if(AssertHelper.isOrNotEmpty_assert(model.getOrgId())){
			//orgName=invoiceReqHService.findOrgNameByOrgCode(model.getOperationOrgCode());
			BizOrgNode node=OrgCacheUtils.getNodeByOrgId(model.getOrgId());
			//BizOrgNode node=OrgCacheUtils.getNodeByOrgCode(model.getOrgId());
			if(null!=node){
				inparam.setOrgName(node.getName());
				inparam.setOrgCode(node.getCode());
			}
		}if(AssertHelper.isOrNotEmpty_assert(model.getCrvatTrxPoolId())){
			pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, model.getCrvatTrxPoolId());
			inparam.setTaxRate(null!=pool.getTaxRate()?pool.getTaxRate().toString():"0");
			inparam.setTrxNumber(pool.getTrxNumber());
			inparam.setTrxDate(pool.getTrxDate().toString());
			amountInParam=getAmout(inparam,pool);
			
			/*****bo.wang******************/
//			inparam.setUsedAmount(amountInParam.getUsedAmount());
//			inparam.setUserfulAmount(amountInParam.getUserfulAmount());
			inparam.setUserfulAmount(invoiceSyncProvider.getUserfulAmountByTrxPoolId(model.getCrvatTrxPoolId()).toString());
			inparam.setUsedAmount(invoiceSyncProvider.getUsedAmount(model.getCrvatTrxPoolId()).toString());
			/*****************bo.wang**********/
			
			//inparam.setTaxTrxTypeName(pool.getTaxBaseName());
		}if(AssertHelper.isOrNotEmpty_assert(model.getTaxTrxTypeId())){
			TmsMdTaxTrxType type=model.getTmsMdTaxTrxType();
			inparam.setTaxTrxTypeCode(type.getTaxTrxTypeCode());
			inparam.setTaxTrxTypeName(type.getTaxTrxTypeName());
		}
		return inparam;
	}
	public InvoiceReqL convertInvoiceReqLInParamToEntity(InvoiceReqLInParam inParam){
		InvoiceReqL entity=new InvoiceReqL();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}

	/**
	 * @author sqing
	 * @see得到页面展示所需的行表金额信息
	 */
	public InvoiceReqLInParam getAmout(InvoiceReqLInParam inparam,InvoiceTrxPool pool){
		Map<String,Object>params=new HashMap();
		params.put("crvatTrxPoolId", inparam.getCrvatTrxPoolId());
		List<InvoiceReqL>list=invoiceReqLDao.findInvoiceReqLByParams(params);
		InvoiceReqLInParam rInParam=new InvoiceReqLInParam();
		BigDecimal amount = BigDecimal.valueOf(0);
		for (int i = 0; i < list.size(); i++) {
			amount=amount.add(list.get(i).getInvoiceAmount());
		}
		/*****bo.wang******************/
//		rInParam.setUserfulAmount((pool.getCurrencyAmount().subtract(amount)).toString());
//		rInParam.setUsedAmount(amount.toString());
		rInParam.setUserfulAmount(invoiceSyncProvider.getUserfulAmountByTrxPoolId(inparam.getCrvatTrxPoolId()).toString());
		rInParam.setUsedAmount(invoiceSyncProvider.getUsedAmount(inparam.getCrvatTrxPoolId()).toString());
		/*****************bo.wang**********/
		
		
		return rInParam;
	}
	
	/**   
	 * @param pooId
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService#findInvoiceReqLByPoolId(java.lang.String)  
	 */
	
	@Override
	public InvoiceReqL findInvoiceReqLByPoolId(String pooId) {
		//InvoiceReqL invoiceReqL = invoiceReqLDao.findInvoiceReqLByPoolId(pooId);
		return null;
	}

	
	/**   
	 * @param invoiceReqLIds  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService#deleteFromReq(java.lang.String[])
	 * @author sqing  
	 */
	
	@Override
	public void deleteFromReq(String[] invoiceReqLIds) {
		InvoiceReqL entity;
		InvoiceTrxPool pool;
		for (int i = 0; i < invoiceReqLIds.length; i++) {
			entity=(InvoiceReqL) this.findById(InvoiceReqL.class, invoiceReqLIds[i]);
			pool=(InvoiceTrxPool) invoiceTrxPoolService.findById(InvoiceTrxPool.class, entity.getCrvatTrxPoolId());
			this.remove(entity);
			pool.setStatus("");
			invoiceTrxPoolService.update(pool);
		}
	}

	
	/**   
	 * @param ids
	 * @param invoiceReqLKeys  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService#saveInvoiceReqLS(java.lang.String[], java.lang.String)  
	 */
	
	@Override
	public void saveInvoiceReqLS(String[] ids,Map<String, Object>map) {
		String hid=map.get("crvatInvoiceReqHId").toString() ;
		InvoiceReqL invoiceReqL;
		//String invoiceReqLKeys = IdGenerator.getPrefixTimestamp("Req", 2);
		/*if(AssertHelper.isOrNotEmpty_assert(hid)){
			InvoiceReqH entity = (InvoiceReqH) invoiceReqHService.get(InvoiceReqH.class, hid);
			invoiceReqHService.update(entity);
		}
		else{
			InvoiceReqH invoiceReqH = new InvoiceReqH();
			invoiceReqH=(InvoiceReqH) ReflectUtils.setProperties(invoiceReqH, map);
			invoiceReqH.setId(invoiceReqLKeys);
			invoiceReqHService.save(invoiceReqH);
		}*/
		for (int i = 0; i < ids.length; i++) {
			String id=ids[i];
			InvoiceTrxPool pool=(InvoiceTrxPool) invoiceTrxPoolService.get(InvoiceTrxPool.class, id);
			InvoiceReqL reqL=(InvoiceReqL) invoiceReqHService.get(InvoiceReqL.class, id);
			if(pool!=null){
				invoiceReqL=this.convertInvoiceTrxPoolToInvoiceReqL(pool,hid);
				if(!AssertHelper.isOrNotEmpty_assert(invoiceReqL.getId())){
					invoiceReqL.setStatus(AppFormStatuEnums.DRAFT.getValue());
					this.save(invoiceReqL);
					pool.setStatus(CrvatTaxPoolStatuEnums.APPFORM_USED.getValue());
					invoiceTrxPoolService.update(pool);
				}
			}	
		}
	}

	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param pool
	 * @param invoiceReqLKeys
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private InvoiceReqL convertInvoiceTrxPoolToInvoiceReqL(InvoiceTrxPool pool,
			String invoiceReqLKeys) {
		InvoiceReqL invoiceReqL = new InvoiceReqL();
		invoiceReqL.setCrvatInvoiceReqHId(invoiceReqLKeys);
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
		return invoiceReqL;
	}

	
	/**   
	 * @param ids  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService#changeStatus(java.lang.String[])  
	 */
	
	@Override
	public void changeStatus(String[] ids,String name) {
		for (int i = 0; i < ids.length; i++) {
			InvoiceTrxPool entity=(InvoiceTrxPool) this.get(InvoiceTrxPool.class, ids[i]);
			entity.setStatus(name);
			invoiceTrxPoolService.update(entity);
		}
	}
}
