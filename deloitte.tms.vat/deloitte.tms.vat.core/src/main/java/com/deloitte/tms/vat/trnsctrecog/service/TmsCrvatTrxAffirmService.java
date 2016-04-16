package com.deloitte.tms.vat.trnsctrecog.service;
// Generated by bo.wang with ling2.autoproject

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatTrxAffirm;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatTrxAffirmInParam;
import com.deloitte.tms.vat.trnsctrecog.dao.TmsCrvatTrxAffirmDao;
/**
 * Home object for domain model class TmsCrvatTrxAffirm.
 * @see com.deloitte.tms.vat.trnsctrecog.model
 * @author Hibernate Tools
 */
public interface TmsCrvatTrxAffirmService extends IService{
	public static final String BEAN_ID="tmsCrvatTrxAffirmService";
	
	public DaoPage findTmsCrvatTrxAffirmByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsCrvatTrxAffirmInParam> findTmsCrvatTrxAffirmByParams(Map params);
	
	public void saveTmsCrvatTrxAffirmDataListsMap(Map dataListsMap);
	
	public TmsCrvatTrxAffirmInParam convertTmsCrvatTrxAffirmToInParam(TmsCrvatTrxAffirm model);
	
	public TmsCrvatTrxAffirm convertTmsCrvatTrxAffirmInParamToEntity(TmsCrvatTrxAffirmInParam inParam);

	public DaoPage findTmsCrvatTrxAffirmByOrganization_id(
			Map<String, Object> parameter, Integer pageNumber, Integer pageSize);
	public DaoPage transactiontype_id(Map<String, Object> parameter,
			Integer pageNumber, Integer pageSize);

	public String loadTransactionException(Map<String, Object> parameter); 
	
}

