package com.deloitte.tms.vat.trnsctrecog.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatSsTrxAll;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatSsTrxAllInParam;
import com.deloitte.tms.vat.trnsctrecog.dao.TmsCrvatSsTrxAllDao;
/**
 * Home object for domain model class TmsCrvatSsTrxAll.
 * @see com.deloitte.tms.vat.trnsctrecog.model
 * @author Hibernate Tools
 */
public interface TmsCrvatSsTrxAllService extends IService{
	public static final String BEAN_ID="tmsCrvatSsTrxAllService";
	
	public DaoPage findTmsCrvatSsTrxAllByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsCrvatSsTrxAllInParam> findTmsCrvatSsTrxAllByParams(Map params);
	
	public void saveTmsCrvatSsTrxAllDataListsMap(Map dataListsMap);
	
	public TmsCrvatSsTrxAllInParam convertTmsCrvatSsTrxAllToInParam(TmsCrvatSsTrxAll model);
	
	public TmsCrvatSsTrxAll convertTmsCrvatSsTrxAllInParamToEntity(TmsCrvatSsTrxAllInParam inParam);
	
}

