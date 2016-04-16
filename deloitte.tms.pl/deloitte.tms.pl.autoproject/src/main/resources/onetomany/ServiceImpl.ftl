<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
package ${pojo.getPackageName()}.service.impl;
// Generated by bo.wang with ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
@Component(${declarationName}Service.BEAN_ID)
public class ${declarationName}ServiceImpl extends BaseService implements ${declarationName}Service{
	@Resource
	${declarationName}Dao ${pojo.getDeclarationNameFirstLetterLower()}Dao;

	@Override
	public IDao getDao() {
		return ${pojo.getDeclarationNameFirstLetterLower()}Dao;
	}

	@Override
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${declarationName}ByParams(params, pageIndex, pageSize);
		daoPage.setResult(convert${declarationName}ToInParam((List<${declarationName}>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<${declarationName}InParam> find${declarationName}ByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<${declarationName}> temp= ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${declarationName}ByParams(params);
		return convert${declarationName}ToInParam(temp);
	}
	@Override
	public void save${declarationName}DataListsMap(Map dataListsMap) {
		Collection<${declarationName}InParam> deleteList = BatchUtils.to(${declarationName}InParam.class).getDeleteEntities(dataListsMap);
		Collection<${declarationName}InParam> insertList =  BatchUtils.to(${declarationName}InParam.class).getInsertEntities(dataListsMap);
		Collection<${declarationName}InParam> updateList =  BatchUtils.to(${declarationName}InParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(${declarationName}InParam inParam: updateList){
				${declarationName} entity=convert${declarationName}InParamToEntity(inParam);
				${pojo.getDeclarationNameFirstLetterLower()}Dao.update(entity);
			}		
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(${declarationName}InParam inParam: insertList){
				${declarationName} entity=convert${declarationName}InParamToEntity(inParam);
				${pojo.getDeclarationNameFirstLetterLower()}Dao.save(entity);
				inParam.setId(entity.getId());
			}
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(${declarationName}InParam inParam:deleteList){
				${declarationName} entity=new ${declarationName}();
				entity.setId(inParam.getId());
				${pojo.getDeclarationNameFirstLetterLower()}Dao.remove(entity);
			}
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.removeAll(deleteList);
		}
		
	}
	private List<${declarationName}InParam> convert${declarationName}ToInParam(List<${declarationName}> models){
		List<${declarationName}InParam> result=new ArrayList<${declarationName}InParam>();
		for(${declarationName} initiation:models){
			${declarationName}InParam inparam=convert${declarationName}ToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public ${declarationName}InParam convert${declarationName}ToInParam(${declarationName} model){
		${declarationName}InParam inparam=new ${declarationName}InParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public ${declarationName} convert${declarationName}InParamToEntity(${declarationName}InParam inParam){
		${declarationName} entity=new ${declarationName}();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
<#assign tempchild=child>
	@Override
	public DaoPage find${childdeclarationName}ByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${childdeclarationName}ByParams(params, pageIndex, pageSize);
		daoPage.setResult(convert${childdeclarationName}ToInParam((List<${childdeclarationName}>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<${childdeclarationName}InParam> find${childdeclarationName}ByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<${childdeclarationName}> temp= ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${childdeclarationName}ByParams(params);
		return convert${childdeclarationName}ToInParam(temp);
	}
	@Override
	public void save${childdeclarationName}DataListsMap(Map dataListsMap) {
		Collection<${childdeclarationName}InParam> deleteList = BatchUtils.to(${childdeclarationName}InParam.class).getDeleteEntities(dataListsMap);
		Collection<${childdeclarationName}InParam> insertList =  BatchUtils.to(${childdeclarationName}InParam.class).getInsertEntities(dataListsMap);
		Collection<${childdeclarationName}InParam> updateList =  BatchUtils.to(${childdeclarationName}InParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(${childdeclarationName}InParam inParam: updateList){
				${childdeclarationName} entity=convert${childdeclarationName}InParamToEntity(inParam);
				${pojo.getDeclarationNameFirstLetterLower()}Dao.update(entity);
			}		
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(${childdeclarationName}InParam inParam: insertList){
				${childdeclarationName} entity=convert${childdeclarationName}InParamToEntity(inParam);
				${pojo.getDeclarationNameFirstLetterLower()}Dao.save(entity);
				inParam.setId(entity.getId());
			}
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(${childdeclarationName}InParam inParam:deleteList){
				${childdeclarationName} entity=new ${childdeclarationName}();
				entity.setId(inParam.getId());
				${pojo.getDeclarationNameFirstLetterLower()}Dao.remove(entity);
			}
//			${pojo.getDeclarationNameFirstLetterLower()}Dao.removeAll(deleteList);
		}
	}
	private List<${childdeclarationName}InParam> convert${childdeclarationName}ToInParam(List<${childdeclarationName}> models){
		List<${childdeclarationName}InParam> result=new ArrayList<${childdeclarationName}InParam>();
		for(${childdeclarationName} initiation:models){
			${childdeclarationName}InParam inparam=convert${childdeclarationName}ToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public ${childdeclarationName}InParam convert${childdeclarationName}ToInParam(${childdeclarationName} model){
		${childdeclarationName}InParam inparam=new ${childdeclarationName}InParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public ${childdeclarationName} convert${childdeclarationName}InParamToEntity(${childdeclarationName}InParam inParam){
		${childdeclarationName} entity=new ${childdeclarationName}();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
	public ${childdeclarationName} find${childdeclarationName}(Map params)
	{
		Object value=params.get("${pojo.getDeclarationNameFirstLetterLower()}Id");
		AssertHelper.notEmpty_assert("${childdeclarationName}关联主键不能为空");
		${declarationName} ${pojo.getDeclarationNameFirstLetterLower()}=(${declarationName}) ${pojo.getDeclarationNameFirstLetterLower()}Dao.get(${declarationName}.class, Long.parseLong(value.toString()));
		//return ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${childdeclarationName}(params);
		${childdeclarationName} result= ${pojo.getDeclarationNameFirstLetterLower()}.get${childdeclarationName}();
		if(result==null)
		{
			result=new ${childdeclarationName}();
		}
		return result;
	}
</#foreach>
}
</#assign>
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
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.model.${declarationName}InParam;
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
import ${child.getPackageName()}.model.${childdeclarationName};
import ${child.getPackageName()}.model.${childdeclarationName}InParam;
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
import ${child.getPackageName()}.model.${childdeclarationName};
import ${child.getPackageName()}.model.${childdeclarationName}InParam;
</#foreach>
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
import ${pojo.getPackageName()}.service.${declarationName}Service;
${classbody}
