package com.deloitte.tms.vat.salesinvoice.service.impl;
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
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxLInParam;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceTrxLDao;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxLService;
/**
 * Home object for domain model class InvoiceTrxL.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Component(InvoiceTrxLService.BEAN_ID)
public class InvoiceTrxLServiceImpl extends BaseService implements InvoiceTrxLService{
	@Resource
	InvoiceTrxLDao invoiceTrxLDao;

	@Override
	public IDao getDao() {
		return invoiceTrxLDao;
	}

	@Override
	public DaoPage findInvoiceTrxLByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceTrxLDao.findInvoiceTrxLByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceTrxLToInParam((List<InvoiceTrxL>) daoPage.getResult()));
		return daoPage;
	}
	
	private List<InvoiceTrxLInParam> convertInvoiceTrxLToInParam(List<InvoiceTrxL> models){
		List<InvoiceTrxLInParam> result=new ArrayList<InvoiceTrxLInParam>();
		for(InvoiceTrxL initiation:models){
			InvoiceTrxLInParam inparam=convertInvoiceTrxLToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public InvoiceTrxLInParam convertInvoiceTrxLToInParam(InvoiceTrxL model){
		InvoiceTrxLInParam inparam=new InvoiceTrxLInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public InvoiceTrxL convertInvoiceTrxLInParamToEntity(InvoiceTrxLInParam inParam){
		InvoiceTrxL entity=new InvoiceTrxL();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
}

