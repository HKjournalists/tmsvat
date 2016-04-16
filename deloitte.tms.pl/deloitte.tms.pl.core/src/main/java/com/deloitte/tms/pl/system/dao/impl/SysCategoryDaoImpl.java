package com.deloitte.tms.pl.system.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.system.dao.SysCategoryDao;
import com.deloitte.tms.pl.system.model.SysCategory;
/**
 * Home object for domain model class SysCategory.
 * @see com.deloitte.tms.pl.system.model
 * @author Hibernate Tools
 */
@Component(SysCategoryDao.BEAN_ID)
public class SysCategoryDaoImpl extends BaseDao<SysCategory> implements SysCategoryDao{
	public DaoPage findSysCategoryByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from SysCategory where 1=1 ");
		Object value=params.get("policyNo");
		if(value!=null&&!"".equals(value))
		{
			query.append(" and policyNo=:policyNo");
			values.put("policyNo", value);
		}
		value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List findSysCategoryByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from SysCategory where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return findBy(query, values);
	}
	public List<SysCategory> loadSysCategory(Map<String, Object> map)
	{
		StringBuffer query=new StringBuffer();
		query.append("from  SysCategory where 1=1 ");
		Map values=new HashMap();
		if(map==null)
		{
			map=new HashMap<String, Object>();
		}
		Object value=map.get(TableColnumDef.FLAG_DEF);
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and "+TableColnumDef.FLAG_DEF+" = :flag");
			values.put("flag",value);
		}
		value=map.get("parentId");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and parent.id = :parentId");
			values.put("parentId",value.toString());
		}else{
			query.append(" and parent.id is null ");
		}
		query.append(" order by sortOrder asc ");
		return findBy(query, values);
	}
	@SuppressWarnings("unchecked")
	public List<SysCategory> findCategoriesBycategoryCode(String categoryCode) {
		StringBuffer query = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		query.append(" from SysCategory category where 1=1 ");
		query.append(" and category.enabled = :enabled");
		query.append(" and category.parent.enabled=:enabled");
		query.append(" and category.flag = :flag");
		query.append(" and category.parent.flag=:flag");
		query.append(" and category.parent.code=:code");
		query.append(" order by sortOrder");
		params.put("enabled", Boolean.TRUE);
		params.put("code", categoryCode);
		params.put("flag", TableColnumDef.FLAG_EFFECT);
		List result= findBy(query, params);
		return result;
	}
	@Override
	public SysCategory getCategoryCode(String code) {
		StringBuffer query = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		query.append(" from SysCategory category where 1=1 ");
		query.append(" and category.enabled = :enabled");
		query.append(" and category.code=:code");
		params.put("code", code);
		params.put("enabled", Boolean.TRUE);
		List result= findBy(query, params);
		return (SysCategory) getFirstRecord(result);
	}
}

