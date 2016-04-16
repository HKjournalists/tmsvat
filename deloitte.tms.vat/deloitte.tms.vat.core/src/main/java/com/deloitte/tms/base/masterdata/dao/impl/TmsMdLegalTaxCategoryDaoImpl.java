package com.deloitte.tms.base.masterdata.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalTaxCategoryDao;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory;
/**
 * Home object for domain model class TmsMdLegalTaxCategory.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdLegalTaxCategoryDao.BEAN_ID)
public class TmsMdLegalTaxCategoryDaoImpl extends BaseDao<TmsMdLegalTaxCategory> implements TmsMdLegalTaxCategoryDao{
	public DaoPage findTmsMdLegalTaxCategoryByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdLegalTaxCategoryQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<TmsMdLegalTaxCategory> findTmsMdLegalTaxCategoryByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildTmsMdLegalTaxCategoryQuery(query, values, params);
		return findBy(query, values);
	}
	private void buildTmsMdLegalTaxCategoryQuery(StringBuffer query,Map values,Map params) {
		query.append(" from TmsMdLegalTaxCategory where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
	}
}

