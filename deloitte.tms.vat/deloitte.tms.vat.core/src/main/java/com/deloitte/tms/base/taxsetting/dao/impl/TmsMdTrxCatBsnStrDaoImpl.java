package com.deloitte.tms.base.taxsetting.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxCatBsnStr;
import com.deloitte.tms.base.taxsetting.dao.TmsMdTrxCatBsnStrDao;
/**
 * Home object for domain model class TmsMdTrxCatBsnStr.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
@Component(TmsMdTrxCatBsnStrDao.BEAN_ID)
public class TmsMdTrxCatBsnStrDaoImpl extends BaseDao<TmsMdTrxCatBsnStr> implements TmsMdTrxCatBsnStrDao{
	public DaoPage findTmsMdTrxCatBsnStrByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdTrxCatBsnStrQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<TmsMdTrxCatBsnStr> findTmsMdTrxCatBsnStrByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdTrxCatBsnStrQuery(query, values, params);
		return findBy(query, values);
	}
	private void buildTmsMdTrxCatBsnStrQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdTrxCatBsnStr where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
	}
}

