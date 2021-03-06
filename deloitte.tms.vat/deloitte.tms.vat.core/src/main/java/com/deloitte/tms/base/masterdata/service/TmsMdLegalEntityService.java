package com.deloitte.tms.base.masterdata.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntityInParam;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
/**
 * Home object for domain model class TmsMdLegalEntity.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface TmsMdLegalEntityService extends IService{
	public static final String BEAN_ID="tmsMdLegalEntityService";
	
	public DaoPage findTmsMdLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdLegalEntityInParam> findTmsMdLegalEntityByParams(Map params);
	
	public void saveTmsMdLegalEntityDataListsMap(Map dataListsMap);
	
	public TmsMdLegalEntityInParam convertTmsMdLegalEntityToInParam(TmsMdLegalEntity model);
	
	public TmsMdLegalEntity convertTmsMdLegalEntityInParamToEntity(TmsMdLegalEntityInParam inParam);

	
}

