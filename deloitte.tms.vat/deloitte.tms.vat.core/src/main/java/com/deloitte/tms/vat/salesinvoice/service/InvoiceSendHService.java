package com.deloitte.tms.vat.salesinvoice.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendHInParam;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSendHDao;
/**
 * Home object for domain model class InvoiceSendH.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface InvoiceSendHService extends IService{
	public static final String BEAN_ID="invoiceSendHService";
	
	public DaoPage findInvoiceSendHByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<InvoiceSendHInParam> findInvoiceSendHByParams(Map params);
	
	public void saveInvoiceSendHDataListsMap(Map dataListsMap);
	
	public InvoiceSendHInParam convertInvoiceSendHToInParam(InvoiceSendH model);
	
	public InvoiceSendH convertInvoiceSendHInParamToEntity(InvoiceSendHInParam inParam);
	
}

