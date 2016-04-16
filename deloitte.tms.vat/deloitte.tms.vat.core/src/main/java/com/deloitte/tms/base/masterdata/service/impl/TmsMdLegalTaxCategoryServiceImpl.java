package com.deloitte.tms.base.masterdata.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategoryInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategoryInParam;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalTaxCategoryDao;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService;
/**
 * Home object for domain model class TmsMdLegalTaxCategory.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdLegalTaxCategoryService.BEAN_ID)
public class TmsMdLegalTaxCategoryServiceImpl extends BaseService implements TmsMdLegalTaxCategoryService{

	
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.core.service.IService#getDao()  
	 */
	
	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**   
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService#findTmsMdLegalTaxCategoryByParams(java.util.Map, java.lang.Integer, java.lang.Integer)  
	 */
	
	@Override
	public DaoPage findTmsMdLegalTaxCategoryByParams(Map params,
			Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService#findTmsMdLegalTaxCategoryByParams(java.util.Map)  
	 */
	
	@Override
	public List<TmsMdLegalTaxCategoryInParam> findTmsMdLegalTaxCategoryByParams(
			Map params) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**   
	 * @param dataListsMap  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService#saveTmsMdLegalTaxCategoryDataListsMap(java.util.Map)  
	 */
	
	@Override
	public void saveTmsMdLegalTaxCategoryDataListsMap(Map dataListsMap) {
		// TODO Auto-generated method stub
		
	}

	
	/**   
	 * @param model
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService#convertTmsMdLegalTaxCategoryToInParam(com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory)  
	 */
	
	@Override
	public TmsMdLegalTaxCategoryInParam convertTmsMdLegalTaxCategoryToInParam(
			TmsMdLegalTaxCategory model) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**   
	 * @param inParam
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService#convertTmsMdLegalTaxCategoryInParamToEntity(com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategoryInParam)  
	 */
	
	@Override
	public TmsMdLegalTaxCategory convertTmsMdLegalTaxCategoryInParamToEntity(
			TmsMdLegalTaxCategoryInParam inParam) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

