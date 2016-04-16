package com.deloitte.tms.pl.system.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.system.dao.SysCategoryDao;
import com.deloitte.tms.pl.system.model.SysCategory;
import com.deloitte.tms.pl.system.model.SysCategoryInParam;
import com.deloitte.tms.pl.system.service.SysCategoryService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
/**
 * Home object for domain model class SysCategory.
 * @see com.deloitte.tms.pl.system.model
 * @author Hibernate Tools
 */
@Component(SysCategoryService.BEAN_ID)
public class SysCategoryServiceImpl extends BaseService implements SysCategoryService{
	@Resource
	SysCategoryDao sysCategoryDao;

	@Override
	public IDao getDao() {
		return sysCategoryDao;
	}

	@Override
	public DaoPage findSysCategoryByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		return sysCategoryDao.findSysCategoryByParams(params, pageIndex, pageSize);
	}
	@Override
	public List findSysCategoryByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		return sysCategoryDao.findSysCategoryByParams(params);
	}
	@Override
	public void saveSysCategoryDataListsMap(Map dataListsMap) {
		Collection<SysCategory> deleteList = BatchUtils.to(SysCategory.class).getDeleteEntities(dataListsMap);
		Collection<SysCategory> insertList =  BatchUtils.to(SysCategory.class).getInsertEntities(dataListsMap);
		Collection<SysCategory> updateList =  BatchUtils.to(SysCategory.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			sysCategoryDao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			sysCategoryDao.saveAll(insertList);
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			sysCategoryDao.removeAll(deleteList);
		}
		
	}
	public List<SysCategory> loadSysCategory(Map<String, Object> map) {
		if(map==null)
		{
			map=new HashMap<String, Object>();
		}
		map.put(TableColnumDef.FLAG_DEF, TableColnumDef.FLAG_EFFECT);
		Object value=map.get("parentId");
		if(AssertHelper.notEmpty(value))
		{
			Object parentId=value.toString();
			map.put("parentId", parentId);
			return sysCategoryDao.loadSysCategory(map);
		}else {
			return sysCategoryDao.loadSysCategory(map);
		}
	}

	@Override
	public List<SysCategory> listEntries(String categoryCode) {
		return sysCategoryDao.findCategoriesBycategoryCode(categoryCode);
	}

	@Override
	public SysCategory getCategoryCode(String code) {
		return sysCategoryDao.getCategoryCode(code);
	}
	
	public void removeSysCategory(String id) throws Exception {
		AssertHelper.notEmpty_assert(id,"主键不能为空");
		SysCategory category=(SysCategory) sysCategoryDao.get(SysCategory.class,id);
		if(category.getChilds().size()>0){
			throw new BusinessException("请先删除子节点");
		}
		category.setFlag(TableColnumDef.FLAG_DISABLED);
		category.setEnabled(false);
		sysCategoryDao.update(category);
	}
}

