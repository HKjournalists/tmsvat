package com.deloitte.tms.base.masterdata.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
import com.deloitte.tms.base.masterdata.dao.BaseUserOrgDao;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
/**
 * Home object for domain model class BaseUserOrg.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(BaseUserOrgDao.BEAN_ID)
public class BaseUserOrgDaoImpl extends BaseDao<BaseUserOrg> implements BaseUserOrgDao{

	
	/**   
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.dao.BaseUserOrgDao#findBaseUserOrgByParams(java.util.Map, java.lang.Integer, java.lang.Integer)  
	 */
	
	@Override
	public DaoPage findBaseUserOrgByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.dao.BaseUserOrgDao#findBaseUserOrgByParams(java.util.Map)  
	 */
	
	@Override
	public List<BaseUserOrg> findBaseUserOrgByParams(Map params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

