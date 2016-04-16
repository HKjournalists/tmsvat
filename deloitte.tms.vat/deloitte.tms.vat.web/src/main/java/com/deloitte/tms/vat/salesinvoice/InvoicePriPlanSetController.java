package com.deloitte.tms.vat.salesinvoice;
// Generated by bo.wang with ling2.autoproject

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePriPlanSet;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePriPlanSetInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePriPlanSetService;
/**
 * Home object for domain model class InvoicePriPlanSet.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("invoicePriPlanSet")
public class InvoicePriPlanSetController extends BaseController{
	@Resource
	InvoicePriPlanSetService invoicePriPlanSetService;
	
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePriPlanSetPage", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadInvoicePriPlanSetPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=invoicePriPlanSetService.findInvoicePriPlanSetByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	
	@RequestMapping(value = "/priPlanSetManageInit", method = RequestMethod.GET)
	public String priPlanSetManageInit()throws Exception{
		return "vat/invoice/invoicePriPlanSet/priPlanSetManageInit";
	}
	@ResponseBody
	@RequestMapping(value = "/saveInvoicePriPlanSet", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveInvoicePriPlanSet(InvoicePriPlanSetInParam inParam) throws Exception {
		/*
		InvoicePriPlanSet entity=invoicePriPlanSetService.convertInvoicePriPlanSetInParamToEntity(inParam);
		if(entity.getId()==null){
			invoicePriPlanSetService.save(entity);
		}
		else{
			invoicePriPlanSetService.update(entity);
		}
		inParam.setId(entity.getId());*/
	}	
	@ResponseBody
	@RequestMapping(value = "/removeInvoicePriPlanSets", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeInvoicePriPlanSets(@RequestParam String invoicePriPlanSetKeys) {
		AssertHelper.notEmpty_assert(invoicePriPlanSetKeys,"需要删除的用户不能为空");
		String[] invoicePriPlanSetIds=invoicePriPlanSetKeys.split(",");
		//for(String invoicePriPlanSetId:invoicePriPlanSetIds){
			
		//}
	}
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePriPlanSet", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<InvoicePriPlanSetInParam> loadInvoicePriPlanSet(Map<String, Object> map) throws Exception {
		List result=invoicePriPlanSetService.findInvoicePriPlanSetByParams(map);
		return result;
	}
	
	public InvoicePriPlanSetInParam loadAddInvoicePriPlanSet(Map<String, Object> map) throws Exception {
		InvoicePriPlanSetInParam inParam=new InvoicePriPlanSetInParam();
		return inParam;
	}
	/**
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/loadModifyInvoicePriPlanSet")
	public InvoicePriPlanSetInParam loadModifyInvoicePriPlanSet(@RequestParam Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		InvoicePriPlanSet entity=(InvoicePriPlanSet)invoicePriPlanSetService.get(InvoicePriPlanSet.class,id.toString());
		InvoicePriPlanSetInParam inParam=invoicePriPlanSetService.convertInvoicePriPlanSetToInParam(entity);
		return inParam;
	}
	
	
	
	public void updateModifyInvoicePriPlanSet(InvoicePriPlanSetInParam inParam) throws Exception {
		InvoicePriPlanSet entity=(InvoicePriPlanSet)invoicePriPlanSetService.get(InvoicePriPlanSet.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		invoicePriPlanSetService.update(entity);
	}
}

