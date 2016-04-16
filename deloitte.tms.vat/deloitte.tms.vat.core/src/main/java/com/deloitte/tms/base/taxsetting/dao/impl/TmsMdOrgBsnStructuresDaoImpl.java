package com.deloitte.tms.base.taxsetting.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.base.taxsetting.dao.TmsMdOrgBsnStructuresDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdOrgBsnStructures;
/**
 * Home object for domain model class TmsMdOrgBsnStructures.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdOrgBsnStructuresDao.BEAN_ID)
public class TmsMdOrgBsnStructuresDaoImpl extends BaseDao<TmsMdOrgBsnStructures> implements TmsMdOrgBsnStructuresDao{
	public DaoPage findTmsMdOrgBsnStructuresByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdOrgBsnStructuresQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<TmsMdOrgBsnStructures> findTmsMdOrgBsnStructuresByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdOrgBsnStructuresQuery(query, values, params);
		return findBy(query, values);
	}
	private void buildTmsMdOrgBsnStructuresQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdOrgBsnStructures where 1=1 ");
		Object value=params.get("status");
		Object value2=params.get("orgId");
		Object value3=params.get("accdFlexStructuresId");
		Object value4=params.get("legalEntityId");
		Object value5=params.get("bsnFlexStructuresId");
		Object value6=params.get("costCenter");
		Object value7=params.get("description");
		Object value8=params.get("sourceCode");
		Object value9=params.get("startDate");
		Object value10=params.get("endDate");
		Object value11=params.get("parentId");
		
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		if(value2!=null && !"".equals(value2))
		{
			query.append(" and orgId=:orgId");
			values.put("orgId", value2);
		}
		if(value3!=null && !"".equals(value3))
		{
			query.append(" and accdFlexStructuresId like :accdFlexStructuresId");
			values.put("accdFlexStructuresId", "%"+value3+"%");
		}	
		if(value4!=null && !"".equals(value4))
		{
			query.append(" and legalEntityId like :legalEntityId");
			values.put("legalEntityId", "%"+value4+"%");
		}
		if(value5!=null && !"".equals(value5))
		{
			query.append(" and bsnFlexStructuresId like :bsnFlexStructuresId");
			values.put("bsnFlexStructuresId", "%"+value5+"%");
		}
		if(value6!=null && !"".equals(value6))
		{
			query.append(" and costCenter like :costCenter");
			values.put("costCenter", "%"+value6+"%");
		}
		if(value7!=null && !"".equals(value7))
		{
			query.append(" and description like :description");
			values.put("description", "%"+value7+"%");
		}
		if(value8!=null && !"".equals(value8))
		{
			query.append(" and sourceCode like :sourceCode");
			values.put("sourceCode", "%"+value8+"%");
		}	
		if(value9!=null && !"".equals(value9))
		{
			query.append(" and startDate like :startDate");
			values.put("startDate", "%"+value9+"%");
		}
		if(value10!=null && !"".equals(value10))
		{
			query.append(" and endDate like :endDate");
			values.put("endDate", "%"+value10+"%");
		}
		if(value11!=null && !"".equals(value11))
		{
			query.append(" and parentId like :parentId");
			values.put("parentId", "%"+value11+"%");
		}
		
		
		
	}
}
