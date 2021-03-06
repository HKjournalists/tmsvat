package com.deloitte.tms.base.masterdata.controller;
// Generated by bo.wang with ling2.autoproject

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEnablePrintService;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.TreeGenerator;
import com.deloitte.tms.vat.core.common.FunctionTreeNode;
/**
 * Home object for domain model class TmsMdLegalEnablePrint.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Controller
public class TmsMdLegalEnablePrintController extends BaseController{
	@Resource
	TmsMdLegalEnablePrintService tmsMdLegalEnablePrintService;
	
	
	
	public static String mainTable= TmsMdLegalEnablePrint.class.getName();
	public static String extrTable=TmsMdLegalEntity.class.getName();
	/**
	 * 
	 * @return
	 * @author tigchen
	 */
	@RequestMapping("legalPrintMgt.do")
	public String goIndexPage(){
		
		return "base/masterdata/legalEnablePrintMgt";
	}
	
	
	/**
	 * 
	 *list no used legal and equipment divided
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
		@RequestMapping("legalPrint/listLegalFree.do")
		public void listLegalFree(HttpServletResponse response){
			
			
			try{
				
				ArrayList<String> needFields = new ArrayList<String>();
				needFields.add("id");
				needFields.add("legalEntityName");
						
			    List<Map<String, Object>> results = this.tmsMdLegalEnablePrintService.listByFilter2Map(extrTable, mainTable, needFields);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);
			    
			    System.out.println("TmsMdLegalEnablePrintController > listLegalFree return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
			    
			}catch(Exception e){
				
				System.out.println(e.getMessage());
				this.returnFail(response);
			}
		}
		
		
		
		
		/**
		 * inin tree of legal equipment on page left
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		
		@RequestMapping("legalPrint/treeLegalPrint.do")//treeLegalPrint -> 
		public void treeUseLocalLegal(HttpServletResponse response){
			
			try{
	
			    List<FunctionTreeNode> treeNodes =  this.tmsMdLegalEnablePrintService.findModelInParaByDefault2Nodes(mainTable, extrTable);
			  
			    List<FunctionTreeNode> results = TreeGenerator.buildTreeUseLegalEntityId(treeNodes);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);	
			    
			    System.out.println("---TmsMdLegalEnablePrintController---treeLegaEquipment return: "+jsonArray);
			    retJsonArray(response, jsonArray);
			    
			}catch(Exception e){
				System.out.println("--TmsMdLegalEnablePrintController --treeLegaEquipment------------------exception as below:");
				System.out.println("--TmsMdLegalEnablePrintController--treeLegaEquipment------------------exception:"+e.getMessage());
			}
		}
		
	
		
		/**
		 * 删除 legal 部门  和 对应的 打印机 关联关系, 把 当前节点的子节点一起删除, 用个check box判断 要不要删除当前节点子节点 以及 删除后怎么处理
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @param legalPrintIds
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		@RequestMapping("legalPrint/delLegalPrint.do")//delLegalPrint->delUseLocalNode
		public void delLegalPrint(HttpServletResponse response,  @RequestParam String legalPrintIds){
			
			try{
			
			if(AssertHelper.empty(legalPrintIds)){
				//to-do
				this.returnOk(response); 
			}else{
				
		
				HashMap<String, String> map = new HashMap<String, String>();
			
					
					map.put("id", legalPrintIds);
				
				
				this.tmsMdLegalEnablePrintService.removeByKeys(map, mainTable);
				 //this.tmsMdLegalEnablePrintService.execDelHqlDefault(map);
				
				returnOk(response);
			}
			
			}catch(Exception e){
				
				this.returnFail(response);
			}
		}
		
		
		
		/**
		 * 
		 * upate 功能
		 * 功能详细描述
		 * @param url
		 * @param response
		 * @throws Exception 
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		
		//public void updateUseLocalLegal(@ModelAttribute("TmsMdLegalEnablePrint") TmsMdLegalEnablePrint TmsMdLegalEnablePrint,
		@RequestMapping("legalPrint/update.do")
		public void updateUseLocalLegal(@RequestParam Map<String, Object> map  ,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				 
				TmsMdLegalEnablePrint tmsMdLegalEnablePrint=new TmsMdLegalEnablePrint(map);
				
				
				if(AssertHelper.empty(tmsMdLegalEnablePrint.getId())){
					
					System.out.println("UsageLocalMgtController>updateLegalPrintFunction>tmsMdLegalEnablePrint no id, should throw exception: tmsMdLegalEnablePrint:"+tmsMdLegalEnablePrint);
					result.put("fail", true);
					result.put("errorMsg", "now department uuid is null"+tmsMdLegalEnablePrint);
					retJson(response, result);
					
			
				}else{
					System.out.println("UsageLocalMgtController>updateLegalPrintFunction>defaultDept has uuid, will update: "+tmsMdLegalEnablePrint);
					this.tmsMdLegalEnablePrintService.update(tmsMdLegalEnablePrint);
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					retJson(response, result);
				}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result.put("msg", getMessage("save.fail"));
					//retJson(response, result);
					e.printStackTrace();
				}
		}
		
		
		/**
		 * 
		 * upate 功能
		 * 功能详细描述
		 * @param url
		 * @param response
		 * @throws Exception 
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		@RequestMapping("legalPrint/addNew.do")
		public void addNewUseLocalLegalUseMap(@RequestParam Map<String, Object> map,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				if(map==null){
					this.returnFail(response);
					return;
				}
				
				TmsMdLegalEnablePrint tmsMdLegalEnablePrint = new TmsMdLegalEnablePrint(map);
				
				if(AssertHelper.empty(tmsMdLegalEnablePrint)){
					this.returnFail(response);
					return;
				}
				
				
			/*	String tempStoredLegalEntityId = tmsMdLegalEnablePrint.getId();
				
				tmsMdLegalEnablePrint.setParentId(tempStoredLegalEntityId);*/
				
				if(!AssertHelper.empty(tmsMdLegalEnablePrint.getId())){
					
					System.out.println("UsageLocalMgtController>addNewUseLocalLegal>tmsMdLegalEnablePrint have id, exception , should have no id");
					
					tmsMdLegalEnablePrint.setId(null);
					
					
				}
				
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdLegalEnablePrint:"+tmsMdLegalEnablePrint);
				System.out.println("useLocalLegal/addNew.do > will save new tmsMdLegalEnablePrint parentId:"+tmsMdLegalEnablePrint.getParentId());
					
				tmsMdLegalEnablePrintService.save(tmsMdLegalEnablePrint);
					
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					
					
					this.returnOk(response);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result.put("errorMsg", getMessage("save.fail"));
					
					e.printStackTrace();
					this.returnFail(response);
				}
		}
		
	
	/**
	 * 
	 *list no used legal and equipment divided
	 * 功能详细描述
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
		//@RequestMapping("legalPrint/listLegalFree.do")
		/*public void listLegalFree(HttpServletResponse response){
			
			
			try{
	//	        select LEGAL_ENTITY_ID from tms_md_legal_entity x where x.LEGAL_ENTITY_ID  not in (select legal_Entity_Id from tms_md_legal_equipment)

				String t1=TmsMdLegalEntity.class.getName();
				String checkT=TmsMdLegalEnablePrint.class.getName();
				
				StringBuffer legalSb= new StringBuffer(" from ");
			
				legalSb.append(t1 ).append("  target where   flag!='1' and target.id not in (select legalEntityId from  ").append(checkT).append(" where flag!='1')");
				
				
				System.out.println("TmsMdLegalEnablePrintController > listLegalFreeNew will run hql: "+legalSb.toString());
			    List<TmsMdLegalEntity>	legalList = tmsMdLegalEnablePrintService.findBy(legalSb, new HashMap());	
				
			    
			    List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
			    
			    
			    if(legalList==null){
			    	
			    	this.returnFail(response);
			    }
			    
			    Iterator<TmsMdLegalEntity> iter =  legalList.iterator();
			   
			    while(iter.hasNext()){
			    	
			    	TmsMdLegalEntity e = iter.next();
			    	
			    	Map<String, Object> map = new HashMap<String, Object>();
			    	
				       map.put("id", e.getId());
				       map.put("legalEntityName",e.getLegalEntityName());
				       
				       results.add(map);
			    }
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);
			    
			    System.out.println("TmsMdLegalEnablePrintController > listLegalFree return: "+jsonArray);
			   
			    this.retJsonArray(response, jsonArray);
			    
			    System.out.println("---------------");
			    
			}catch(Exception e){
				
				System.out.println(e.getMessage());
				this.returnFail(response);
			}
		}
		*/
		
		
		/**
		 * inin tree of legal equipment on page left
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
		
		//@RequestMapping("legalPrint/treeLegalPrint.do")
		/*public void treeLegalPrint(HttpServletResponse response){
			
			try{				
				
				List<TmsMdLegalEnablePrintInParam> legalPrintInPara = this.tmsMdLegalEnablePrintService.findModelInParaByDefault();

			    List<FunctionTreeNode> treeNodes = FunctionTreeNode.generalConvertTreeNodeList4InPara(legalPrintInPara);
			  
			    List<FunctionTreeNode> results = TreeGenerator.buildTree(treeNodes);
			    
			    JSONArray jsonArray = JSONArray.fromObject(results);	
			    
			    System.out.println("------treeLegaEquipment return: "+jsonArray);
			    retJsonArray(response, jsonArray);
			    
			}catch(Exception e){
				System.out.println("treeLegaEquipment------------------exception as below:");
				System.out.println("treeLegaEquipment------------------exception:"+e.getMessage());
			}
		}*/
	
		/**
		 * 删除 legal 部门  和 对应的 打印机 关联关系, 把 当前节点的子节点一起删除, 用个check box判断 要不要删除当前节点子节点 以及 删除后怎么处理
		 *〈一句话功能简述〉 
		 * 功能详细描述
		 * @param response
		 * @param legalPrintIds
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		//@RequestMapping("legalPrint/delLegalPrint.do")
		/*	public void delLegalPrint(HttpServletResponse response, String legalPrintIds){
			
			try{
			
			if(AssertHelper.empty(legalPrintIds)){
				//to-do
				this.returnOk(response); 
			}else{
				
				
				//String[] ids = legalPrintIds.split(IDSPE);
				
				
				if(ids==null || ids.length<1){
					this.returnOk(response);
				}
								 
				// String targetClassName = TmsMdLegalEnablePrint.class.getName();
				
				 //this.tmsMdLegalEnablePrintService.delModelByDefault(targetClassName, ids);
				
				HashMap<String, String> map = new HashMap<String, String>();
				//for(String id : ids){
					
					map.put("id", legalPrintIds);
				//}
				
				
				this.tmsMdLegalEnablePrintService.execDelHqlDefaultTrue(map);
				 //this.tmsMdLegalEnablePrintService.execDelHqlDefault(map);
				
				returnOk(response);
			}
			
			}catch(Exception e){
				
				this.returnFail(response);
			}
		}*/
		
		
		
		/**
		 * 
		 * 把纳税主体 加入树， 作为当前选择 纳税主体的 下级 
		 * 功能详细描述
		 * @param response
		 * @param tmsMdLegalEquipment
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		//@RequestMapping("legalPrint/addLegalIntoTree.do")
		public void addLegalIntoTree(String nowLegalEquId, String willNewLegalid, String willNewLegalName, HttpServletResponse response){/*
			
			System.out.println("legalPrint/addLegalIntoTree.do > addLegalIntoTree---------");
			
			try{
				
				
			if( AssertHelper.empty(willNewLegalid)){
				//to-do
				return;
			}else{

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("legalEntityId", willNewLegalid);
				map.put("parentId", nowLegalEquId);
				this.tmsMdLegalEnablePrintService.execInsertHqlDefault(map);
				

				
				
			TmsMdLegalEnablePrint newEnitty = new TmsMdLegalEnablePrint();
				
				
				if(nowLegalEquId==null || "".equals(nowLegalEquId.trim())){
					nowLegalEquId=null;
				}
				
				newEnitty.setParentId(nowLegalEquId);
				newEnitty.setLegalEntityId(willNewLegalid);
				
				newEnitty.setIsEnablePrint(false);
				newEnitty.setEnabledFlag(false);
				newEnitty.setFlag(TableColnumDef.FLAG_EFFECT);
				
				this.tmsMdLegalEnablePrintService.save(newEnitty);

				this.returnOk( response);
				
				
			}
			
			}catch(Exception e){
				
				e.printStackTrace();
				this.returnFail(response);
			}
		*/}
		
		
		/**
		 * 
		 * upate 功能
		 * 功能详细描述
		 * @param url
		 * @param response
		 * @throws Exception 
		 * @see [相关类/方法]（可选）
		 * @since [产品/模块版本] （可选）
		 * @author tigchen
		 */
		
	//	@RequestMapping("legalPrint/update.do")
		/*public void updateLegalPrintFunction(@ModelAttribute("tmsMdLegalEnablePrint") TmsMdLegalEnablePrint tmsMdLegalEnablePrint,
				HttpServletResponse response)  {
			JSONObject result = new JSONObject();
			try {
				
				if(AssertHelper.empty(tmsMdLegalEnablePrint.getId())){
					
					System.out.println("TmsMdLegalEnablePrintController>updateLegalPrintFunction>tmsMdLegalEnablePrint no uuid, should throw exception: tmsMdLegalEnablePrint:"+tmsMdLegalEnablePrint);
					result.put("fail", true);
					result.put("errorMsg", "now department uuid is null"+tmsMdLegalEnablePrint);
					retJson(response, result);
					
					//throw new Exception("now department uuid is null"+defaultDept);
					
					
					 * defaultDept.setId(IdGenerator.getUUID());
					
					
					deptService.save(defaultDept);
				}else{
					System.out.println("TmsMdLegalEnablePrintController>updateLegalPrintFunction>defaultDept has uuid, will update: "+tmsMdLegalEnablePrint);
					this.tmsMdLegalEnablePrintService.update(tmsMdLegalEnablePrint);
					
					result.put("success", true);
					result.put("msg", getMessage("save.sucess"));
					retJson(response, result);
				}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result.put("msg", getMessage("save.fail"));
					//retJson(response, result);
					e.printStackTrace();
				}
		}*/
		
		

}

