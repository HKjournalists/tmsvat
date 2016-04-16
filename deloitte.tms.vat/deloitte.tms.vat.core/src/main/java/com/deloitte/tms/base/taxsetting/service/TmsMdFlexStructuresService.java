package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.taxsetting.dao.TmsMdFlexStructuresDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexSegments;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexSegmentsInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexStructures;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexStructuresInParam;
/**
 * Home object for domain model class TmsMdFlexStructures.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface TmsMdFlexStructuresService extends IService{
	public static final String BEAN_ID="tmsMdFlexStructuresService";
	
	public DaoPage findTmsMdFlexStructuresByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdFlexStructuresInParam> findTmsMdFlexStructuresByParams(Map params);
	
	public void saveTmsMdFlexStructuresDataListsMap(Map dataListsMap);
	
	public TmsMdFlexStructuresInParam convertTmsMdFlexStructuresToInParam(TmsMdFlexStructures model);
	
	public TmsMdFlexStructures convertTmsMdFlexStructuresInParamToEntity(TmsMdFlexStructuresInParam inParam);
	
	public DaoPage findTmsMdFlexSegmentsByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdFlexSegmentsInParam> findTmsMdFlexSegmentsByParams(Map params);
	
	public void saveTmsMdFlexSegmentsDataListsMap(Map dataListsMap);
	
	public TmsMdFlexSegmentsInParam convertTmsMdFlexSegmentsToInParam(TmsMdFlexSegments model);
	
	public TmsMdFlexSegments convertTmsMdFlexSegmentsInParamToEntity(TmsMdFlexSegmentsInParam inParam);
	
}

