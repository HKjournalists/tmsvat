package com.deloitte.tms.vat.salesinvoice.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReverseReq;
/**
 * Home object for domain model class TmsCrvatInvReverseReq.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface TmsCrvatInvReverseReqDao extends IDao<TmsCrvatInvReverseReq>{
	public static final String BEAN_ID="tmsCrvatInvReverseReqDao";
	public DaoPage findTmsCrvatInvReverseReqObjectByParams(Map params, Integer pageIndex,Integer pageSize);
	public void removeCrvatInvReverseReqById(String id);
	
	public List<String> resetCrvatTaxPoolStatus(String invoiceCode,String invoiceNumber,String status);
	public List<Object[]> checkExistVoice(String invoiceCode, String invoiceNumber,String informationNumber);
	
}

