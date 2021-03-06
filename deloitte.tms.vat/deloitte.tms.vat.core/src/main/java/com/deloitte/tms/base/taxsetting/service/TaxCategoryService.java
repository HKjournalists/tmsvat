package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.taxsetting.model.TaxCategory;
import com.deloitte.tms.base.taxsetting.model.TaxCategoryInParam;
import com.deloitte.tms.base.taxsetting.dao.TaxCategoryDao;
import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.base.taxsetting.model.ItemsInParam;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
/**
 * Home object for domain model class TaxCategory.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
public interface TaxCategoryService extends IService{
	public static final String BEAN_ID="taxCategoryService";
	
	public DaoPage findTaxCategoryByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TaxCategoryInParam> findTaxCategoryByParams(Map params);
	
	public void saveTaxCategoryDataListsMap(Map dataListsMap);
	
	public TaxCategoryInParam convertTaxCategoryToInParam(TaxCategory model);
	
	public TaxCategory convertTaxCategoryInParamToEntity(TaxCategoryInParam inParam);
	
	public DaoPage findItemsByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<ItemsInParam> findItemsByParams(Map params);
	
	public void saveItemsDataListsMap(Map dataListsMap);
	
	public ItemsInParam convertItemsToInParam(Items model);
	
	public Items convertItemsInParamToEntity(ItemsInParam inParam);
	
	/**
	 * @author sqing
	 */
	public String findTaxCategoryNameById(String categoryId);
	
	public List findBySearchTax(String hql);
}

