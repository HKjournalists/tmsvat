package com.deloitte.tms.vat.controller;
// Generated by bo.wang with ling2.autoproject

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.model.provider.Order;
import com.deloitte.tms.vat.model.User;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * Home object for domain model class Order.
 * @see com.deloitte.tms.pl.security.model
 * @author Hibernate Tools
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController{
	/*@Resource
	OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadOrderPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=orderService.findOrderByParams(parameter,PageUtils.getPageIndex(parameter),PageUtils.getPageSize(parameter));
		return daoPage;
	}
	@ResponseBody
	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void saveOrder(OrderInParam inParam) throws Exception {
		Order entity=orderService.convertOrderInParamToEntity(inParam);
		if(entity.getId()==null){
			orderService.save(entity);
		}
		else{
			orderService.update(entity);
		}
		inParam.setId(entity.getId());
	}*/
	@ResponseBody
	@RequestMapping(value = "/getOrders")
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public List<User> loadOrderPages(HttpServletRequest request) throws Exception {
		String usernameString = request.getParameter("name");
		String emailString = request.getParameter("email");
		String userId = getUserId();
		Locale locale =  getLocale();
		
		System.out.println(usernameString);
		List<User> list = new ArrayList<User>();
		for(int i=0;i<5;i++){
			User user = new User();
			user.setUserId(i+"");
			user.setUserName(userId+i);
			user.setPhoneNo(i+1+"");
			list.add(user);
		}
		return list;
		
	}
/*	
	public Collection<OrderInParam> loadOrder(Map<String, Object> map) throws Exception {
		List result=orderService.findOrderByParams(map);
		return result;
	}
	
	public OrderInParam loadAddOrder(Map<String, Object> map) throws Exception {
		OrderInParam inParam=new OrderInParam();
		return inParam;
	}
	
	public OrderInParam loadModifyOrder(Map<String, Object> map) throws Exception {
		Object id=map.get("id");
		AssertHelper.notEmpty_assert(id,"编辑的主键不能为空");
		Order entity=(Order)orderService.get(Order.class,Long.parseLong(id.toString()));
		OrderInParam inParam=orderService.convertOrderToInParam(entity);
		return inParam;
	}
	
	
	
	public void updateModifyOrder(OrderInParam inParam) throws Exception {
		Order entity=(Order)orderService.get(Order.class,inParam.getId());
		ReflectUtils.copyProperties(inParam, entity);
		orderService.update(entity);
	}*/
}

