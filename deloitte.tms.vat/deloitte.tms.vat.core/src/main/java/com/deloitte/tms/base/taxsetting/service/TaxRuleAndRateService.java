package com.deloitte.tms.base.taxsetting.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.base.taxsetting.model.TaxRuleAndRate;
import com.deloitte.tms.base.taxsetting.model.TaxRuleAndRateInParam;
import com.deloitte.tms.base.taxsetting.dao.TaxRuleAndRateDao;
/**
 * Home object for domain model class TaxRuleAndRate.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
public interface TaxRuleAndRateService extends IService{
	public static final String BEAN_ID="taxRuleAndRateService";
	
	public DaoPage findTaxRuleAndRateByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TaxRuleAndRateInParam> findTaxRuleAndRateByParams(Map params);
	
	public void saveTaxRuleAndRateDataListsMap(Map dataListsMap);
	
	public TaxRuleAndRateInParam convertTaxRuleAndRateToInParam(TaxRuleAndRate model);
	
	public TaxRuleAndRate convertTaxRuleAndRateInParamToEntity(TaxRuleAndRateInParam inParam);
	
}

