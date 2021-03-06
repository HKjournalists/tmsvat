package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.taxsetting.model.TmsMdBsnCombination;
import com.deloitte.tms.base.taxsetting.model.TmsMdBsnCombinationInParam;
import com.deloitte.tms.base.taxsetting.dao.TmsMdBsnCombinationDao;
/**
 * Home object for domain model class TmsMdBsnCombination.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
public interface TmsMdBsnCombinationService extends IService{
	public static final String BEAN_ID="tmsMdBsnCombinationService";
	
	public DaoPage findTmsMdBsnCombinationByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdBsnCombinationInParam> findTmsMdBsnCombinationByParams(Map params);
	
	public void saveTmsMdBsnCombinationDataListsMap(Map dataListsMap);
	
	public TmsMdBsnCombinationInParam convertTmsMdBsnCombinationToInParam(TmsMdBsnCombination model);
	
	public TmsMdBsnCombination convertTmsMdBsnCombinationInParamToEntity(TmsMdBsnCombinationInParam inParam);
	
}

