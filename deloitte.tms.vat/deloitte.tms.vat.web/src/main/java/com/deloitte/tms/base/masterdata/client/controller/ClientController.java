package com.deloitte.tms.base.masterdata.client.controller;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.masterdata.model.Client;
import com.deloitte.tms.base.masterdata.model.ClientInParam;
import com.deloitte.tms.base.masterdata.service.ClientService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.controller.BaseController;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
/**
 * Home object for domain model class Client.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("client")
public class ClientController extends BaseController{
	@Resource
	ClientService clientService;
	
	@ResponseBody
	@RequestMapping(value = "/getClients")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadClientPage(HttpServletResponse response,@RequestParam Map<String,Object> parameter) throws Exception {
		
		DaoPage daoPage=clientService.findClientByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		/*JSONObject clientListObject = new JSONObject();
		clientListObject.put("total", daoPage.getRecordCount());
		clientListObject.put("rows", daoPage.getResult());
		retJson(response, clientListObject);*/
		return daoPage;
	}
	
	@RequestMapping(value = "/clientManageInit", method = RequestMethod.GET)
	public String clientManageInit()throws Exception{
		return "vat/client/clientManageInit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClientNameList")
	public List<ClientInParam> getClientNameList(HttpServletResponse response)throws Exception{
		List<ClientInParam> list = new ArrayList<ClientInParam>();
		ClientInParam clientInParam = new ClientInParam();
			clientInParam.setId("1");
			clientInParam.setClientName("招商银行");
		list.add(clientInParam);
		
		ClientInParam clientInParam1 = new ClientInParam();
		clientInParam1.setId("2");
		clientInParam1.setClientName("成都银行");
		list.add(clientInParam1);
		
		ClientInParam clientInParam2 = new ClientInParam();
		clientInParam2.setId("3");
		clientInParam2.setClientName("银行");
		list.add(clientInParam2);
		
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/getClientInfoList")
	public List<ClientInParam> getClientInfoList(HttpServletResponse response)throws Exception{
		
		List<ClientInParam> list = new ArrayList<ClientInParam>();
		ClientInParam clientInParam = new ClientInParam();
			clientInParam.setId("1");
			clientInParam.setClientName("招商银行");
		list.add(clientInParam);
		
		ClientInParam clientInParam1 = new ClientInParam();
		clientInParam1.setId("2");
		clientInParam1.setClientName("成都银行");
		list.add(clientInParam1);
		
		ClientInParam clientInParam2 = new ClientInParam();
		clientInParam2.setId("3");
		clientInParam2.setClientName("银行");
		list.add(clientInParam2);
		
		return list;
		
	}
	@ResponseBody
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public ClientInParam saveClient(@ModelAttribute("clientManageForm") ClientInParam inParam,HttpServletResponse response) throws Exception {
		Client entity=clientService.convertClientInParamToEntity(inParam);
		if(entity.getId()==null||entity.getId().equals("")){
			clientService.save(entity);
		}else{
			clientService.update(entity);
		}
		inParam.setId(entity.getId());
		return inParam;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removeClients")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void removeClients(@RequestParam("id") String clientKeys,HttpServletResponse response) throws Exception {
		AssertHelper.notEmpty_assert(clientKeys,"需要删除的用户不能为空");
		for(String clientId:clientKeys.split(",")){
			Client entity=(Client)clientService.get(Client.class,clientId);
			clientService.remove(entity);
		}
	}
	
	public Collection<ClientInParam> loadClient(Map<String, Object> map) throws Exception {
		List result=clientService.findClientByParams(map);
		return result;
	}
	
	public ClientInParam loadAddClient(Map<String, Object> map) throws Exception {
		ClientInParam inParam=new ClientInParam();
		return inParam;
	}
	@ResponseBody
	@RequestMapping(value = "/loadModifyClient")
	public ClientInParam loadModifyClient(@RequestParam Map<String, Object> map,HttpServletResponse response) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"需要删除的用户不能为空");
		Client entity=(Client)clientService.get(Client.class,id.toString());
		ClientInParam inParam=clientService.convertClientToInParam(entity);
		return inParam;
	}
	
	
	
	public void updateModifyClient(ClientInParam inParam) throws Exception {
		Client entity=(Client)clientService.get(Client.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		clientService.update(entity);
	}
	/**
	public void loadClientSec(Page<ClientSecInParam> page, Map<String, Object> map) throws Exception {
		DaoPage daoPage=clientService.findClientSecByParams(map, page.getPageNo(), page.getPageSize());
		D7PageUtils.daoPageToPage(daoPage, page);
	}
	*/
	public Collection loadClientSec(Map<String, Object> map) throws Exception {
		List result=clientService.findClientSecByParams(map);
		return result;
	}
	
	/**
	public void saveClientSec(Collection<ClientSecInParam> objs) throws Exception {
		Map results= D7PageUtils.assembleDatasetMap(objs);
		clientService.saveClientSecDataListsMap(results);
	}
	*/
}

