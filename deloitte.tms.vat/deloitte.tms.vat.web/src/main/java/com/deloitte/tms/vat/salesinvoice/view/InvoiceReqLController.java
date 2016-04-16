package com.deloitte.tms.vat.salesinvoice.view;
// Generated by bo.wang with ling2.autoproject

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.vat.base.enums.AppFormStatuEnums;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceTrxPoolService;
/**
 * Home object for domain model class InvoiceReqL.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Controller
//@RequestMapping("invoiceReqL")
public class InvoiceReqLController extends BaseController{
	@Resource
	InvoiceReqLService invoiceReqLService;
	@Autowired
	InvoiceTrxPoolService invoiceTrxPoolService;
	@ResponseBody
	@RequestMapping(value = "/loadInvoiceReqLPage", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadInvoiceReqLPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=invoiceReqLService.findInvoiceReqLByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	@ResponseBody
	@RequestMapping(value = "/saveInvoiceReqL", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveInvoiceReqL(InvoiceReqLInParam inParam) throws Exception {
		InvoiceReqL entity=invoiceReqLService.convertInvoiceReqLInParamToEntity(inParam);
		if(entity.getId()==null){
			invoiceReqLService.save(entity);
		}
		else{
			invoiceReqLService.update(entity);
		}
		inParam.setId(entity.getId());
	}	
	@ResponseBody
	@RequestMapping(value = "invoiceReqL/removeInvoiceReqLs")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeInvoiceReqLs(@RequestParam String invoiceReqLKeys) {
		AssertHelper.notEmpty_assert(invoiceReqLKeys,"需要删除的用户不能为空");
		String[] invoiceReqLIds=invoiceReqLKeys.split(",");
		invoiceReqLService.deleteFromReq(invoiceReqLIds);
	}
	@ResponseBody
	@RequestMapping(value = "/loadInvoiceReqL", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<InvoiceReqLInParam> loadInvoiceReqL(Map<String, Object> map) throws Exception {
		List result=invoiceReqLService.findInvoiceReqLByParams(map);
		return result;
	}
	@RequestMapping(value = "invoiceReqL/getInvoiceReqL", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void getInvoiceReqL(Map<String, Object> map,HttpServletResponse response) throws Exception {
		List result=invoiceReqLService.findInvoiceReqLByParams(map);
		JSONArray jsonArray = JSONArray.fromObject(result);
		retJsonArray(response, jsonArray); 
		/*return result;*/
	}
	public InvoiceReqLInParam loadAddInvoiceReqL(Map<String, Object> map) throws Exception {
		InvoiceReqLInParam inParam=new InvoiceReqLInParam();
		return inParam;
	}
	
	public InvoiceReqLInParam loadModifyInvoiceReqL(Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		InvoiceReqL entity=(InvoiceReqL)invoiceReqLService.get(InvoiceReqL.class,Long.parseLong(id.toString()));
		InvoiceReqLInParam inParam=invoiceReqLService.convertInvoiceReqLToInParam(entity);
		return inParam;
	}
	
	
	
	public void updateModifyInvoiceReqL(InvoiceReqLInParam inParam) throws Exception {
		InvoiceReqL entity=(InvoiceReqL)invoiceReqLService.get(InvoiceReqL.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		invoiceReqLService.update(entity);
	}
	
	@RequestMapping(value = "invoiceReqLAdd/saveDetailReqL", method = RequestMethod.POST)
	public void saveDetailReqL(@RequestParam Map<String, Object>map,HttpServletResponse response) throws IOException{
		String invoiceReqLKeys=map.get("ids").toString();
		//String crvatInvoiceReqHId=map.get("crvatInvoiceReqHId").toString();
		JSONObject object=new JSONObject();
		try {
			String []ids=invoiceReqLKeys.split(",");
			invoiceReqLService.saveInvoiceReqLS(ids,map);
			object.put("msg", "保存成功");
			object.put("success", "true");
		} catch (Exception e) {
			object.put("msg", "保存失败");
			object.put("success", "false");
			e.printStackTrace();
		}
		retJson(response, object);
	}
	@RequestMapping(value = "invoiceReqL/changeStatus", method = RequestMethod.POST)
	public void changeStatus(@RequestParam Map<String, Object>map,HttpServletResponse response){
		String []ids=map.get("ids").toString().split(";");
		String name=ContextUtils.getCurrentUserName();
		invoiceReqLService.changeStatus(ids,name);
	}
}

