package com.deloitte.tms.base.masterdata.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdOrgLegalEntityDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseOrgInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntityInParam;
import com.deloitte.tms.base.masterdata.service.TmsMdOrgLegalEntityService;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
/**
 * Home object for domain model class TmsMdOrgLegalEntity.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdOrgLegalEntityService.BEAN_ID)
public class TmsMdOrgLegalEntityServiceImpl extends BaseService implements TmsMdOrgLegalEntityService{
	@Resource
	TmsMdOrgLegalEntityDao tmsMdOrgLegalEntityDao;
	@Autowired
	BaseOrgDao baseOrgDao;
	@Override
	public IDao getDao() {
		return tmsMdOrgLegalEntityDao;
	}
	
	
	/**
	 * @author tigchen
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public DaoPage findOrgLegalByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsMdOrgLegalEntityDao.findOrgLegalByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertOrgLegalToInParam( ( List<Object[]> )daoPage.getResult() )  );
		return daoPage;
	}
	
	
/**
 * 主表格信息
 */
	@Override
	public DaoPage findTmsMdOrgLegalEntityByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsMdOrgLegalEntityDao.findTmsMdOrgLegalEntityByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdOrgLegalEntityToInParam((List<TmsMdOrgLegalEntity>) daoPage.getResult()));
		return daoPage;
	}
	
	@Override
	public List<TmsMdOrgLegalEntityInParam> findTmsMdOrgLegalEntityByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsMdOrgLegalEntity> temp= tmsMdOrgLegalEntityDao.findTmsMdOrgLegalEntityByParams(params);
		return convertTmsMdOrgLegalEntityToInParam(temp);
	}
	@Override
	public void saveTmsMdOrgLegalEntityDataListsMap(Map dataListsMap) {
		Collection<TmsMdOrgLegalEntityInParam> deleteList = BatchUtils.to(TmsMdOrgLegalEntityInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsMdOrgLegalEntityInParam> insertList =  BatchUtils.to(TmsMdOrgLegalEntityInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsMdOrgLegalEntityInParam> updateList =  BatchUtils.to(TmsMdOrgLegalEntityInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsMdOrgLegalEntityInParam inParam: updateList){
				TmsMdOrgLegalEntity entity=convertTmsMdOrgLegalEntityInParamToEntity(inParam);
				tmsMdOrgLegalEntityDao.update(entity);
			}		
//			tmsMdOrgLegalEntityDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsMdOrgLegalEntityInParam inParam: insertList){
				TmsMdOrgLegalEntity entity=convertTmsMdOrgLegalEntityInParamToEntity(inParam);
				tmsMdOrgLegalEntityDao.save(entity);
			//	inParam.setId(entity.getId());
			}
//			tmsMdOrgLegalEntityDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsMdOrgLegalEntityInParam inParam:deleteList){
				TmsMdOrgLegalEntity entity=new TmsMdOrgLegalEntity();
			//	entity.setId(inParam.getId());
				tmsMdOrgLegalEntityDao.remove(entity);
			}
//			tmsMdOrgLegalEntityDao.removeAll(deleteList);
		}
		
	}
	@Override
	public DaoPage findAllBaseOrg(Map<String, Object>parameter, int pageNumber, int pageSize){		
		DaoPage list= tmsMdOrgLegalEntityDao.findAllBaseOrg(parameter, pageNumber, pageSize);
		return list;
	}
	private List<BaseOrgInParam>convertTmsMdOrgEntityToInParam(List<BaseOrg> models){
		List<BaseOrgInParam>result=new ArrayList<BaseOrgInParam>();
		for (BaseOrg initiation:models) {
			BaseOrgInParam inParam = convertBaseOrgToInParam(initiation);
			result.add(inParam);
		}
		return result;
	}
	public BaseOrgInParam convertBaseOrgToInParam(BaseOrg model){
		BaseOrgInParam inParam = new BaseOrgInParam();
		ReflectUtils.copyProperties(model, inParam);
		inParam.setOrgId(model.getId());
		return inParam;
	}
	/**
	 * 查询主体表与组织表
	 * @param models
	 * @return
	 */
	private List<TmsMdOrgLegalEntityInParam> convertTmsMdOrgLegalEntityToInParam(List<TmsMdOrgLegalEntity> models){
		List<TmsMdOrgLegalEntityInParam> result=new ArrayList<TmsMdOrgLegalEntityInParam>();
		for(TmsMdOrgLegalEntity initiation:models){
			TmsMdOrgLegalEntityInParam inparam=convertTmsMdOrgLegalEntityToInParam(initiation);
			String value = inparam.getLegalEntityType();
			String text = DictionaryCacheUtils.getCodeName("BASE_LEGAL_ENTITY_TYPE", value);
			System.out.println(text);
			inparam.setLegalEntityTypeName(text);
			
			TmsMdLegalEntity legalEntity = tmsMdOrgLegalEntityDao.findLegalEntityById(initiation.getLegalEntityId());//去主体表查询
			BaseOrg baseOrg = null;
			if(initiation.getOrgId()!=null){
			baseOrg = (BaseOrg) baseOrgDao.get(BaseOrg.class, initiation.getOrgId());//查询组织表
			}
			if(legalEntity != null){
				
				inparam.setRegistrationNumber(legalEntity.getRegistrationNumber());
				System.out.println(legalEntity.getRegistrationNumber()+"--------------------------------------------");
				inparam.setLegalEntityName(legalEntity.getLegalEntityName());
				inparam.setLegalEntityCode(legalEntity.getLegalEntityCode());
			}
			if(baseOrg != null){
				
				inparam.setOrgName(baseOrg.getOrgName());
				inparam.setOrgCode(baseOrg.getOrgCode());
			}
			result.add(inparam);
		}
		return result;
	}
	public TmsMdOrgLegalEntityInParam convertTmsMdOrgLegalEntityToInParam(TmsMdOrgLegalEntity model){
		TmsMdOrgLegalEntityInParam inparam=new TmsMdOrgLegalEntityInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public TmsMdOrgLegalEntity convertTmsMdOrgLegalEntityInParamToEntity(TmsMdOrgLegalEntityInParam inParam){
		TmsMdOrgLegalEntity entity=new TmsMdOrgLegalEntity();
		ReflectUtils.copyProperties(inParam, entity);
		entity.setLegalEntityId(inParam.getLegalEntityId());
		entity.setOrgId(inParam.getOrgId());
		return entity;
	}
	/**
	 * 弹出框纳税人识别码
	 */
	@Override
	public DaoPage dialoglegalEntitysearch(Map<String, Object> parameter,
			int pageNumber, int pageSize) {
		DaoPage list= tmsMdOrgLegalEntityDao.dialoglegalEntitysearch(parameter, pageNumber, pageSize);
		return list;
	}
/**
 * 主面板组织查询
 */
	@Override
	public DaoPage orgsearch(Map<String, Object> parameter, int pageNumber,
			int pageSize) {
		DaoPage daoPage = tmsMdOrgLegalEntityDao.orgsearch(parameter,pageNumber,pageSize);
		return daoPage;
	}
	
	
	private List<TmsMdOrgLegalEntityInParam> convertOrgLegalToInParam(List<Object[]> models){
		
		List<TmsMdOrgLegalEntityInParam> result=new ArrayList<TmsMdOrgLegalEntityInParam>();
		
		for(Object[] initiation : models){
			//TmsMdOrgLegalEntityInParam inparam=convertTmsMdOrgLegalEntityToInParam(initiation);
			
			TmsMdOrgLegalEntityInParam inparam= new TmsMdOrgLegalEntityInParam(initiation );
			String value = inparam.getLegalEntityType();
			String text = DictionaryCacheUtils.getCodeName("BASE_LEGAL_ENTITY_TYPE", value);
			System.out.println(text);
			inparam.setLegalEntityTypeName(text);
			
			result.add(inparam);
		}
		return result;
	}
	
}

