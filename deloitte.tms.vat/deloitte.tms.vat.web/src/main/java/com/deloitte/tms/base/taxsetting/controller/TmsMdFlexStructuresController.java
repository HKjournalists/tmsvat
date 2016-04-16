package com.deloitte.tms.base.taxsetting.controller;
// Generated by bo.wang with ling2.autoproject

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
//import com.deloitte.tms.pl.core.commons.springmvc.controller.BaseController;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexSegments;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexSegmentsInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexStructures;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexStructuresInParam;
import com.deloitte.tms.base.taxsetting.service.TmsMdFlexStructuresService;
/**
 * Home object for domain model class TmsMdFlexStructures.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("tmsMdFlexStructures")
public class TmsMdFlexStructuresController extends BaseController{
	@Resource
	TmsMdFlexStructuresService tmsMdFlexStructuresService;
	
	@RequestMapping(value = "/initTmsMdFlexStructures", method = RequestMethod.GET)
	public String initTmsMdFlexStructures()throws Exception{
		return "vat/tmsmdflexstructures/tmsMdFlexStructures";
	}
	
	@ResponseBody            
	@RequestMapping(value = "/loadTmsMdFlexStructuresPage", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void loadTmsMdFlexStructuresPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
		DaoPage daoPage=tmsMdFlexStructuresService.findTmsMdFlexStructuresByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
	
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();  
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd")); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
		//return daoPage;
	}
	@ResponseBody
	@RequestMapping(value = "/saveTmsMdFlexStructures", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveTmsMdFlexStructures(TmsMdFlexStructuresInParam inParam) throws Exception {
		TmsMdFlexStructures entity=tmsMdFlexStructuresService.convertTmsMdFlexStructuresInParamToEntity(inParam);
		if(AssertHelper.empty(entity.getId())){
			entity.setId(IdGenerator.getUUID());									
			tmsMdFlexStructuresService.save(entity);			
		}
		else{				
			tmsMdFlexStructuresService.update(entity);
		}
		inParam.setId(entity.getId());
	}	
	@ResponseBody
	@RequestMapping(value = "/removeTmsMdFlexStructuress", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeTmsMdFlexStructuress(@RequestParam String ids) {
		AssertHelper.notEmpty_assert(ids,"需要删除的用户不能为空");
		String[] tmsMdFlexStructuresIds=ids.split(",");
		//for(String tmsMdFlexStructuresId:tmsMdFlexStructuresIds){
			
		//}
	}
	@ResponseBody
	@RequestMapping(value = "/loadTmsMdFlexStructures", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<TmsMdFlexStructuresInParam> loadTmsMdFlexStructures(@RequestParam Map<String, Object> map) throws Exception {
		List result=tmsMdFlexStructuresService.findTmsMdFlexStructuresByParams(map);
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/loadAddTmsMdFlexStructures", method = RequestMethod.POST)
	public TmsMdFlexStructuresInParam loadAddTmsMdFlexStructures(@RequestParam Map<String, Object> map) throws Exception {
		TmsMdFlexStructuresInParam inParam=new TmsMdFlexStructuresInParam();
		return inParam;
	}
	@ResponseBody
	@RequestMapping(value = "/loadModifyTmsMdFlexStructures", method = RequestMethod.POST)
	public TmsMdFlexStructuresInParam loadModifyTmsMdFlexStructures(@RequestParam Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		TmsMdFlexStructures entity=(TmsMdFlexStructures)tmsMdFlexStructuresService.get(TmsMdFlexStructures.class,id.toString());
		TmsMdFlexStructuresInParam inParam=tmsMdFlexStructuresService.convertTmsMdFlexStructuresToInParam(entity);
			
		return inParam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateModifyTmsMdFlexStructures", method = RequestMethod.POST)
	public void updateModifyTmsMdFlexStructures(TmsMdFlexStructuresInParam inParam) throws Exception {
		TmsMdFlexStructures entity=(TmsMdFlexStructures)tmsMdFlexStructuresService.get(TmsMdFlexStructures.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		tmsMdFlexStructuresService.update(entity);
	}
	@ResponseBody
	@RequestMapping(value = "loadTmsMdFlexSegmentsPage", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void loadTmsMdFlexSegmentsPage(@RequestParam Map<String,Object> parameter,HttpServletResponse response) throws Exception {
		parameter.put("structureId", "");
		DaoPage daoPage=tmsMdFlexStructuresService.findTmsMdFlexSegmentsByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
			
		JSONObject result = new JSONObject();
		 JsonConfig jsonConfig = new JsonConfig();  
		 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		 JSONArray jsonArray = JSONArray.fromObject(daoPage.getResult(),jsonConfig);
		result.put("total", daoPage.getRecordCount());
		result.put("rows", jsonArray);// daoPage.getr
		result.put("pages", daoPage.getPageCount());
		result.put("success", true);
		retJson(response, result);
			
		//return daoPage;
	}
	@ResponseBody
	@RequestMapping(value = "loadTmsMdFlexSegments", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection loadTmsMdFlexSegments(Map<String, Object> map) throws Exception {
		List result=tmsMdFlexStructuresService.findTmsMdFlexSegmentsByParams(map);
		return result;
	}
	@ResponseBody
	@RequestMapping(value = "/saveTmsMdFlexSegments", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveTmsMdFlexSegments(TmsMdFlexSegmentsInParam inParam) throws Exception {
		TmsMdFlexSegments entity=tmsMdFlexStructuresService.convertTmsMdFlexSegmentsInParamToEntity(inParam);
		if(AssertHelper.empty(entity.getId())){
			entity.setId(IdGenerator.getUUID());	

			tmsMdFlexStructuresService.save(entity);
		}
		else{
			tmsMdFlexStructuresService.update(entity);
		}
		inParam.setId(entity.getId());
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/loadModifyTmsMdFlexSegmentsInParam", method = RequestMethod.POST)
	public TmsMdFlexSegmentsInParam loadModifyTmsMdFlexSegments(@RequestParam Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		TmsMdFlexSegments entity=(TmsMdFlexSegments)tmsMdFlexStructuresService.get(TmsMdFlexSegments.class,id.toString());
		TmsMdFlexSegmentsInParam inParam=tmsMdFlexStructuresService.convertTmsMdFlexSegmentsToInParam(entity);		                                                      
		return inParam;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/loadModifyTmsMdFlexSegmentsInParamUpdate", method = RequestMethod.POST)
	public TmsMdFlexStructuresInParam loadModifyTmsMdFlexSegmentsUpdate(TmsMdFlexStructuresInParam inParam) throws Exception {
		Object id = inParam.getId();
		
		
		TmsMdFlexStructures entity=tmsMdFlexStructuresService.convertTmsMdFlexStructuresInParamToEntity(inParam);
//		TmsMdFlexStructures entity=(TmsMdFlexStructures)tmsMdFlexStructuresService.get(TmsMdFlexStructures.class,inParam.getId());	

		tmsMdFlexStructuresService.update(entity);		
		TmsMdFlexStructuresInParam inParam1=tmsMdFlexStructuresService.convertTmsMdFlexStructuresToInParam(entity);		                                                      

		return inParam1;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/loadSelect", method = RequestMethod.POST)
	public TmsMdFlexStructuresInParam loadTmsMdFlexStructuresPageSelect(TmsMdFlexStructuresInParam inParam) throws Exception {
								
		TmsMdFlexStructures entity=tmsMdFlexStructuresService.convertTmsMdFlexStructuresInParamToEntity(inParam);
				
		TmsMdFlexStructuresInParam inParam1=tmsMdFlexStructuresService.convertTmsMdFlexStructuresToInParam(entity);		                                                      
	
		tmsMdFlexStructuresService.update(entity);
		return inParam1;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getResult", method = RequestMethod.POST)
	public List<TmsMdFlexStructuresInParam> getResult(@RequestParam Map<String,Object> parameter) throws Exception {
		
		List<TmsMdFlexStructuresInParam> result = tmsMdFlexStructuresService.findTmsMdFlexStructuresByParams(parameter);
		
	                                             
		return result;
	}
	
	
	
	
}


