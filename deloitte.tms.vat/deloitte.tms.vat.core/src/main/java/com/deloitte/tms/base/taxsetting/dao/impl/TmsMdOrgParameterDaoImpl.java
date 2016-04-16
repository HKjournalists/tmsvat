package com.deloitte.tms.base.taxsetting.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.base.taxsetting.dao.TmsMdOrgParameterDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdOrgParameter;
/**
 * Home object for domain model class TmsMdOrgParameter.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdOrgParameterDao.BEAN_ID)
public class TmsMdOrgParameterDaoImpl extends BaseDao<TmsMdOrgParameter> implements TmsMdOrgParameterDao{
	public DaoPage findTmsMdOrgParameterByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdOrgParameterQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<TmsMdOrgParameter> findTmsMdOrgParameterByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdOrgParameterQuery(query, values, params);
		return findBy(query, values);
	}
	private void buildTmsMdOrgParameterQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdOrgParameter where 1=1 ");
		Object value=params.get("status");
//		Object value2=params.get("orgParameterType");
		Object value3=params.get("orgParameter");
		Object value4=params.get("orgParameterValue");
		Object value5=params.get("description");
		Object value6=params.get("enabledFlag");
		String value7=(String)params.get("startDate");
		String value8=(String)params.get("endDate");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		
//		if(value2!=null && !"".equals(value2))
//		{
//			query.append(" and orgParameterType like :orgParameterType");
//			values.put("orgParameterType", "%"+value2+"%");
//		}
		if(value3!=null &&  !"".equals(value3))
		{
			query.append(" and orgParameter like :orgParameter");
			values.put("orgParameter", "%"+value3+"%");
		}
		if(value4!=null &&  !"".equals(value4))
		{
			query.append(" and orgParameterValue  =:orgParameterValue");
			values.put("orgParameterValue", value4);
		}
		
		if(value5!=null && !"".equals(value5))
		{
			query.append(" and description like :description");
			values.put("description", "%"+value5+"%");
		}
		if(value6!=null && !"".equals(value6))
		{
			query.append(" and enabledFlag = :enabledFlag");
			values.put("enabledFlag", value6);
		}
		if(value7!=null && !"".equals(value7))
		{
			query.append(" and startDate >= :startDate");
			values.put("startDate", DateUtils.parseTime(value7, "yyyy-MM-dd"));
			
		}
		if(value8!=null &&  !"".equals(value8))
		{
			query.append(" and endDate <= :endDate");
			values.put("endDate", DateUtils.parseTime(value8, "yyyy-MM-dd"));
			
			
	
			
			
		}
		
	}
}

