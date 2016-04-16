package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.taxsetting.dao.TmsMdTrxAffirmSettingDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSettingInParam;
/**
 * Home object for domain model class TmsMdTrxAffirmSetting.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface TmsMdTrxAffirmSettingService extends IService{
	public static final String BEAN_ID="tmsMdTrxAffirmSettingService";
	
	public DaoPage findTmsMdTrxAffirmSettingByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdTrxAffirmSettingInParam> findTmsMdTrxAffirmSettingByParams(Map params);
	
	public void saveTmsMdTrxAffirmSettingDataListsMap(Map dataListsMap);
	
	public TmsMdTrxAffirmSettingInParam convertTmsMdTrxAffirmSettingToInParam(TmsMdTrxAffirmSetting model);
	
	public TmsMdTrxAffirmSetting convertTmsMdTrxAffirmSettingInParamToEntity(TmsMdTrxAffirmSettingInParam inParam);
	
}

