package com.deloitte.tms.pl.system.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.system.dao.UserOperationDao;
import com.deloitte.tms.pl.system.model.UserOperation;
/**
 * Home object for domain model class UserOperation.
 * @see com.deloitte.tms.pl.system.model
 * @author Hibernate Tools
 */
@Component(UserOperationDao.BEAN_ID)
public class UserOperationDaoImpl extends BaseDao<UserOperation> implements UserOperationDao{
	public DaoPage findUserOperationByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildUserOperationQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<UserOperation> findUserOperationByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildUserOperationQuery(query, values, params);
		return findBy(query, values);
	}
	private void buildUserOperationQuery(StringBuffer query,Map values,Map params) {
		query.append(" from UserOperation where 1=1 ");
		Object value=params.get("relationId");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and relationId=:relationId");
			values.put("relationId", value);
		}
		value=params.get("groupId");
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and groupId=:groupId");
			values.put("groupId", value);
		}
	}
}

