package com.deloitte.tms.vat.salesinvoice.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendLInParam;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSendLDao;
/**
 * Home object for domain model class InvoiceSendL.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface InvoiceSendLService extends IService{
	public static final String BEAN_ID="invoiceSendLService";
	
	public DaoPage findInvoiceSendLByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<InvoiceSendLInParam> findInvoiceSendLByParams(Map params);
	
	public void saveInvoiceSendLDataListsMap(Map dataListsMap);
	
	public InvoiceSendLInParam convertInvoiceSendLToInParam(InvoiceSendL model);
	
	public InvoiceSendL convertInvoiceSendLInParamToEntity(InvoiceSendLInParam inParam);
	
	public List<InvoiceSendL> findInvoiceSendLByHID(StringBuffer query);
	
}

