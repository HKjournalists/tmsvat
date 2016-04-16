package com.deloitte.tms.vat.salesinvoice.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxH;
/**
 * Home object for domain model class InvoiceTrxH.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface InvoiceTrxHDao extends IDao<InvoiceTrxH>{
	public static final String BEAN_ID="invoiceTrxHDao";
	public DaoPage findInvoiceTrxHByParams(Map params, Integer pageIndex,Integer pageSize);

}

