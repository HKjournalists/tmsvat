package com.deloitte.tms.vat.salesinvoice.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.base.enums.CrvatReverseReqResEnums;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReverseReq;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReverseReqInParam;
import com.deloitte.tms.vat.salesinvoice.dao.TmsCrvatInvReverseReqDao;
/**
 * Home object for domain model class TmsCrvatInvReverseReq.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
public interface TmsCrvatInvReverseReqService extends IService{
	public static final String BEAN_ID="tmsCrvatInvReverseReqService";
	
	public DaoPage findTmsCrvatInvReverseReqByParams(Map params, Integer pageIndex,Integer pageSize);//*matter
	
	public void removeCrvatInvReverseReqById(String id);
	
	public List<String> resetCrvatTaxPoolStatus(String invoiceCode,String invoiceNumber);

	public CrvatReverseReqResEnums checkExistVoice(String invoiceCode, String invoiceNumber, String informationNumber);
}

