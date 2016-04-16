package com.deloitte.tms.base.masterdata.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
/**
 * Home object for domain model class BaseOrg.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface BaseOrgDao extends IDao<BaseOrg>{
	public static final String BEAN_ID="baseOrgDao";
	public DaoPage findBaseOrgByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<BaseOrg> findBaseOrgByParams(Map params);
	public List<BaseOrg> findAllBaseOrg();
	public List<BaseUserOrg> findAllBaseUserOrg();
	/** 
	 * 根据orgCode查询
	 * @param orgCode
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public BaseOrg findBaseOrgByCode(String orgCode);
}

