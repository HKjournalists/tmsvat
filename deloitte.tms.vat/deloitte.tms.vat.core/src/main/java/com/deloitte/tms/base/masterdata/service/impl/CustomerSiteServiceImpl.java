package com.deloitte.tms.base.masterdata.service.impl;
// Generated by bo.wang with ling2.autoproject

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
import com.deloitte.tms.base.masterdata.model.CustomerSite;
import com.deloitte.tms.base.masterdata.model.CustomerSiteInParam;
import com.deloitte.tms.base.masterdata.dao.CustomerSiteDao;
import com.deloitte.tms.base.masterdata.service.CustomerSiteService;
/**
 * Home object for domain model class CustomerSite.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(CustomerSiteService.BEAN_ID)
public class CustomerSiteServiceImpl extends BaseService implements CustomerSiteService{
	@Resource
	CustomerSiteDao customerSiteDao;

	@Override
	public IDao getDao() {
		return customerSiteDao;
	}

	@Override
	public DaoPage findCustomerSiteByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= customerSiteDao.findCustomerSiteByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertCustomerSiteToInParam((List<CustomerSite>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<CustomerSiteInParam> findCustomerSiteByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<CustomerSite> temp= customerSiteDao.findCustomerSiteByParams(params);
		return convertCustomerSiteToInParam(temp);
	}
	@Override
	public void saveCustomerSiteDataListsMap(Map dataListsMap) {
		Collection<CustomerSiteInParam> deleteList = BatchUtils.to(CustomerSiteInParam.class).getDeleteEntities(dataListsMap);
		Collection<CustomerSiteInParam> insertList =  BatchUtils.to(CustomerSiteInParam.class).getInsertEntities(dataListsMap);
		Collection<CustomerSiteInParam> updateList =  BatchUtils.to(CustomerSiteInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(CustomerSiteInParam inParam: updateList){
				CustomerSite entity=convertCustomerSiteInParamToEntity(inParam);
				customerSiteDao.update(entity);
			}		
//			customerSiteDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(CustomerSiteInParam inParam: insertList){
				CustomerSite entity=convertCustomerSiteInParamToEntity(inParam);
				customerSiteDao.save(entity);
				inParam.setId(entity.getId());
			}
//			customerSiteDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(CustomerSiteInParam inParam:deleteList){
				CustomerSite entity=new CustomerSite();
				entity.setId(inParam.getId());
				customerSiteDao.remove(entity);
			}
//			customerSiteDao.removeAll(deleteList);
		}
		
	}
	private List<CustomerSiteInParam> convertCustomerSiteToInParam(List<CustomerSite> models){
		List<CustomerSiteInParam> result=new ArrayList<CustomerSiteInParam>();
		for(CustomerSite initiation:models){
			CustomerSiteInParam inparam=convertCustomerSiteToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public CustomerSiteInParam convertCustomerSiteToInParam(CustomerSite model){
		CustomerSiteInParam inparam=new CustomerSiteInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public CustomerSite convertCustomerSiteInParamToEntity(CustomerSiteInParam inParam){
		CustomerSite entity=new CustomerSite();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
}

