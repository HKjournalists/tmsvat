package com.deloitte.tms.vat.bill.pm.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.bill.pm.model.BillBook;
import com.deloitte.tms.vat.bill.pm.model.BillBookInParam;
import com.deloitte.tms.vat.bill.pm.dao.BillBookDao;
/**
 * Home object for domain model class BillBook.
 * @see com.deloitte.tms.vat.bill.pm.model
 * @author Hibernate Tools
 */
public interface BillBookService extends IService{
	public static final String BEAN_ID="billBookService";
	
	public DaoPage findBillBookByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<BillBookInParam> findBillBookByParams(Map params);
	
	public void saveBillBookDataListsMap(Map dataListsMap);
	
	public BillBookInParam convertBillBookToInParam(BillBook model);
	
	public BillBook convertBillBookInParamToEntity(BillBookInParam inParam);
	
}

