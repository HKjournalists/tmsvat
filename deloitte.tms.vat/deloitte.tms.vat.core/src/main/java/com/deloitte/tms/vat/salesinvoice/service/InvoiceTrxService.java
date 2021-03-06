package com.deloitte.tms.vat.salesinvoice.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxD;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxDataDictInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxLInParam;
/**
 * Home object for domain model class InvoiceTrx.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface InvoiceTrxService extends IService{
	public static final String BEAN_ID="invoiceTrxService";
	
	public List<InvoiceTrxH> getLatestInvoiceTrxH();
	
	public DaoPage findInvoiceTrxByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public DaoPage findInvoiceTrxLByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public InvoiceTrxD getInvoice(String invoiceCode, String invoiceNo);
	
	public List<InvoiceTrxD> findInvoiceTrxDByParams(Map params);
	
	public List<InvoiceTrxL> findAllInvoiceTrxL(Map params);
	
	public List<InvoiceTrxHInParam> findInvoiceTrxByParams(Map params);
	
	public void saveInvoiceTrxDataListsMap(Map dataListsMap);
	
	public void saveInvoiceTrxLDataListsMap(Map dataListsMap);
	
	public void deleteTrx(String id);
	
	public void deleteTrHById(String id);
	
	public void auditInvoiceTrhById(String id);
	
	public void deleteTrxL(String id);
	
	public InvoiceTrxHInParam convertInvoiceTrxToInParam(InvoiceTrxH model);
	
	public InvoiceTrxLInParam convertInvoiceTrxLToInParam(InvoiceTrxL model);
	
	public InvoiceTrxH convertInvoiceTrxInParamToEntity(InvoiceTrxHInParam inParam);

	public InvoiceTrxL convertInvoiceTrxLInParamToEntity(InvoiceTrxLInParam inParam);
	
	public void saveInvoiceTrxH(InvoiceTrxH invoiceTrxH);

}

