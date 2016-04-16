package com.deloitte.tms.vat.salesinvoice.dao.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceSendH;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSendHDao;
/**
 * Home object for domain model class InvoiceSendH.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Component(InvoiceSendHDao.BEAN_ID)
public class InvoiceSendHDaoImpl extends BaseDao<InvoiceSendH> implements InvoiceSendHDao{
	public DaoPage findInvoiceSendHByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildInvoiceSendHQuery(query, values, params);
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List<InvoiceSendH> findInvoiceSendHByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		buildInvoiceSendHQuery(query, values, params);
		return findBy(query, values);
	}
	
	
	private void buildInvoiceSendHQuerys(StringBuffer query,Map values,Map params) {
		query.append(" from InvoiceSendH where 1=1 ");
		String expressNoValue =(String) params.get("expressNo");
		String deliveryStatusValue =(String) params.get("deliveryStatus");
		String deliveryByValue =(String) params.get("deliveryBy");
		String expressCompanyNoValue =(String) params.get("expressCompany");
		String customerNameValue =(String) params.get("customerName");	
		 if(expressNoValue!=null)
			{
				query.append(" and expressNo=:expressNo");
				values.put("expressNo", expressNoValue);
			}
		 if(deliveryStatusValue!=null)
			{
				query.append(" and deliveryStatus=:deliveryStatus");
				values.put("deliveryStatus", deliveryStatusValue);
			}
		 if(deliveryByValue!=null)
			{
				query.append(" and deliveryBy=:deliveryBy");
				values.put("deliveryBy", deliveryByValue);
			}
//		 if(expressCompanyNoValue!=null)
//			{
//				query.append(" and expressCompanyNo=:expressCompanyNo");
//				values.put("expressCompanyNo", expressCompanyNoValue);
//			}
		 if(customerNameValue!=null)
			{
				query.append(" and customerName=:customerName");
				values.put("customerName", customerNameValue);
			}
		
	}
	private void buildInvoiceSendHQuery(StringBuffer query,Map values,Map params) {
		query.append(" from InvoiceSendH where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
	}
}

