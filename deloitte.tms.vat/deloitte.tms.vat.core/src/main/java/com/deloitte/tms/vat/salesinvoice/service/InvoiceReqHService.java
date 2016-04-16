package com.deloitte.tms.vat.salesinvoice.service;
// Generated by bo.wang with ling2.autoproject

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceReqHDao;
/**
 * Home object for domain model class InvoiceReqH.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface InvoiceReqHService extends IService{
	public static final String BEAN_ID="invoiceReqHService";
	
	public DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<InvoiceReqHInParam> findInvoiceReqHByParams(Map params);
	
	public void saveInvoiceReqHDataListsMap(Map dataListsMap);
	
	public InvoiceReqHInParam convertInvoiceReqHToInParam(InvoiceReqH model);
	
	public InvoiceReqH convertInvoiceReqHInParamToEntity(InvoiceReqHInParam inParam);
	
	public InvoiceReqH getInvoiceReqH(String id);
	
	public DaoPage findInvoiceTrxPoolByParams(Map params, Integer pageIndex,Integer pageSize) throws ParseException;
	
	public void saveInvoiceReqAll(InvoiceReqHInParam inParam,Map params);
	public void updateCommit(String id,Map params);
	public DaoPage findInvoiceReqAll(Map<String, Object>map, Integer pageIndex,
			Integer pageSize);
	
	public String findOrgNameByOrgCode(String orgCode);
	
	public Customer getCustomerParam(Map<String, Object> map);
	
	public InvoiceReqHInParam getEditInfo(String id);
	
	public void deleteFromReqAll(String[] ids);
	
	public void saveInvoiceReqHead(InvoiceReqH invoiceReqH);
	
	public void saveInvoiceReqHeadAndRel(InvoiceReqH invoiceReqH);
	
	public void deleteInvoiceReLByReHId(String id);
	
	public void updateInvoiceReq(InvoiceReqH invoiceReqH);
	
	/*public void updateInvoiceReqH(InvoiceReqH invoiceReqH);*/
	public InvoiceReqL convertInvoiceTrxPoolToInvoiceReqL(InvoiceTrxPool pool,InvoiceReqL entity);
	
	public String getRowsids(String id);
	
	public void updateAdminStatus(String name);
	
	public String setUpHead(Map<String, Object>map) throws ParseException;
}

