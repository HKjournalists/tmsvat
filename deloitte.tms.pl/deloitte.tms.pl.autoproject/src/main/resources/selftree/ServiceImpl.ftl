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
	public List find${declarationName}ByParams(Map params) {
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
	public List<${declarationName}InParam> load${declarationName}(Map<String, Object> map) {
		List<${declarationName}> temp;
		if(map==null)
		{
			map=new HashMap<String, Object>();
		}
		Object value=map.get("parentId");
		if(AssertHelper.notEmpty(value))
		{
			Long parentId=Long.parseLong(value.toString());
			//${declarationName} ${pojo.getDeclarationNameFirstLetterLower()}=(${declarationName}) ${pojo.getDeclarationNameFirstLetterLower()}Dao.get(${declarationName}.class, parentId);
			//temp= (List<${declarationName}>)${pojo.getDeclarationNameFirstLetterLower()}.getChilds();
			map.put("parentId", parentId);
			temp=${pojo.getDeclarationNameFirstLetterLower()}Dao.load${declarationName}(map);
		}else {
			temp=${pojo.getDeclarationNameFirstLetterLower()}Dao.load${declarationName}(map);
		}
		return convert${declarationName}ToInParam(temp);
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
}
</#assign>
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.BatchUtils;
import com.ling2.core.commons.utils.reflect.ReflectUtils;
import com.ling2.core.dao.IDao;
import com.ling2.core.service.impl.BaseService;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.model.${declarationName}InParam;
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
import ${pojo.getPackageName()}.service.${declarationName}Service;
${classbody}
