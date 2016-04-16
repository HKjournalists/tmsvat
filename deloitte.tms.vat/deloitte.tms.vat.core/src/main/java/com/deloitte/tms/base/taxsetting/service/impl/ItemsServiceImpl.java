package com.deloitte.tms.base.taxsetting.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.ItemsDao;
import com.deloitte.tms.base.taxsetting.dao.TaxCategoryDao;
import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.base.taxsetting.model.ItemsInParam;
import com.deloitte.tms.base.taxsetting.service.ItemsService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.core.common.MessageHelp;
/**
 * Home object for domain model class Items.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
@Component(ItemsService.BEAN_ID)
public class ItemsServiceImpl extends BaseService implements ItemsService{
	@Resource
	ItemsDao itemsDao;
	@Autowired
	TaxCategoryDao categoryDao;
	@Override
	public IDao getDao() {
		return itemsDao;
	}
	
	

	@Override
	public DaoPage findItemsByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= itemsDao.findItemsByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertItemsToInParam((List<Items>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<ItemsInParam> findItemsByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<Items> temp= itemsDao.findItemsByParams(params);
		return convertItemsToInParam(temp);
	}
	@Override
	public void saveItemsDataListsMap(Map dataListsMap) {
		Collection<ItemsInParam> deleteList = BatchUtils.to(ItemsInParam.class).getDeleteEntities(dataListsMap);
		Collection<ItemsInParam> insertList =  BatchUtils.to(ItemsInParam.class).getInsertEntities(dataListsMap);
		Collection<ItemsInParam> updateList =  BatchUtils.to(ItemsInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(ItemsInParam inParam: updateList){
				Items entity=convertItemsInParamToEntity(inParam);
				itemsDao.update(entity);
			}		
//			itemsDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(ItemsInParam inParam: insertList){
				Items entity=convertItemsInParamToEntity(inParam);
				itemsDao.save(entity);
				inParam.setId(entity.getId());
			}
//			itemsDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(ItemsInParam inParam:deleteList){
				Items entity=new Items();
				entity.setId(inParam.getId());
				itemsDao.remove(entity);
			}
//			itemsDao.removeAll(deleteList);
		}
		
	}
	private List<ItemsInParam> convertItemsToInParam(List<Items> models){
		List<ItemsInParam> result=new ArrayList<ItemsInParam>();
		String categoryId,categoryName;
		for(Items initiation:models){
			ItemsInParam inparam=convertItemsToInParam(initiation);
			categoryId=initiation.getCategoryId();
			categoryName=categoryDao.findTaxCategoryNameById(categoryId);
			inparam.setCategoryName(categoryName);
			result.add(inparam);
		}
		return result;
	}
	public ItemsInParam convertItemsToInParam(Items model){
		ItemsInParam inparam=new ItemsInParam();
		String categoryName=categoryDao.findTaxCategoryNameById(model.getCategoryId());
		ReflectUtils.copyProperties(model, inparam);
		inparam.setCategoryName(categoryName);
		return inparam;
	}
	public Items convertItemsInParamToEntity(ItemsInParam inParam){
		Items entity=new Items();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
}
