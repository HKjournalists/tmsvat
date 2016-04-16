package com.deloitte.tms.vat.trnsctrecog.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.trnsctrecog.model.SourceSubject;
import com.deloitte.tms.vat.trnsctrecog.model.SourceSubjectInParam;
/**
 * Home object for domain model class SourceSubject.
 * @see com.deloitte.tms.vat.bill.pm.model
 * @author Hibernate Tools
 */
public interface ISourceSubjectService extends IService{
	public static final String BEAN_ID="sourceSubjectService";
	
	public DaoPage findSourceSubjectByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public DaoPage findSourceSubjectPartByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<SourceSubjectInParam> findSourceSubjectByParams(Map params);
	
	public void saveSourceSubjectDataListsMap(Map dataListsMap);
	
	public SourceSubjectInParam convertSourceSubjectToInParam(SourceSubject model);
	
	public SourceSubject convertSourceSubjectInParamToEntity(SourceSubjectInParam inParam);
	
}

