package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItemsInParam;
import com.deloitte.tms.base.taxsetting.dao.TmsMdInventoryItemsDao;
/**
 * Home object for domain model class TmsMdInventoryItems.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
public interface TmsMdInventoryItemsService extends IService{
	public static final String BEAN_ID="tmsMdInventoryItemsService";
	
	public DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdInventoryItemsInParam> findTmsMdInventoryItemsByParams(Map params);
	
	public void saveTmsMdInventoryItemsDataListsMap(Map dataListsMap);
	
	public TmsMdInventoryItemsInParam convertTmsMdInventoryItemsToInParam(TmsMdInventoryItems model);
	
	public TmsMdInventoryItems convertTmsMdInventoryItemsInParamToEntity(TmsMdInventoryItemsInParam inParam);
	
}

