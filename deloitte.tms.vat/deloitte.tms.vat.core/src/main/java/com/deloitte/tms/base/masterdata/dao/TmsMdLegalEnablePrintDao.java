package com.deloitte.tms.base.masterdata.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrintInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
/**
 * Home object for domain model class TmsMdLegalEnablePrint.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface TmsMdLegalEnablePrintDao extends IDao<TmsMdLegalEnablePrint>{
	public static final String BEAN_ID="tmsMdLegalEnablePrintDao";
	public DaoPage findTmsMdLegalEnablePrintByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<TmsMdLegalEnablePrint> findTmsMdLegalEnablePrintByParams(Map params);
	public TmsMdLegalEnablePrint findTmsMdLegalEnablePrint(Map params);
	public TmsMdLegalEntity findTmsMdLegalEntity(Map params);
	
	public List<Object[]>  findModelInParaByDefault();
	

	public void delModelByDefaultUseRemove(TmsMdLegalEnablePrint target);
	public List<Object[]> listByFilter(String orgClass, String filterClass,
			ArrayList<String> fieldList);
	public void removeByKeysUseFlag(HashMap keysMap, String targetClassName);
	public List<Object[]> findModelInParaByDefault(String mainTable, String extrTable);
	
	public List<TmsMdLegalEnablePrint> findAllTmsMdLegalEnablePrint();
}

