package com.deloitte.tms.vat.salesinvoice;
// Generated by bo.wang with ling2.autoproject

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LeafNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.service.impl.LegalEntityCacheProvider;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.base.masterdata.dao.EquipmentDao;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEnablePrintService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEquipmentService;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.vat.base.enums.InvoicePrintStatusEnums;
import com.deloitte.tms.vat.base.enums.InvoiceReqTypeEnums;
import com.deloitte.tms.vat.base.enums.VatCRInvoiceTypeEnums;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.FPZLEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.HSBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.KPBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.QDBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueHead;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxInterface;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintApplyOrgInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintLegalEntityInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolL;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvoicePreH;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePrintPoolDService;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePrintPoolHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePrintPoolLService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceReqHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider;

@Controller
@RequestMapping("invoicePrintPoolH")
public class InvoicePrintPoolHController extends BaseController{
	@Resource
	InvoicePrintPoolHService invoicePrintPoolHService;
	
	@Resource
	InvoicePrintPoolLService invoicePrintPoolLService;
	
	@Resource
	InvoiceReqHService invoiceReqHService;
	
	@Resource
	InvoicePrintPoolDService invoicePrintPoolDService;
	/**
	 * 发票库存对接
	 */
	@Resource 
	InvoiceSyncProvider invoiceSyncProvider;
	
	@Resource
	DictionaryService dictionaryService;
	
	@Resource
	TmsMdLegalEquipmentService legalEquipmentService;
	
	@Resource(name=TaxInterface.BEAN_ID)
	TaxInterface taxInterface;
	
	
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolHPage2222", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadInvoicePrintPoolHPage11(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=invoicePrintPoolHService.findSqelQueryInvoicePrintPoolHByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		Collection<Object[]> resultList=(Collection<Object[]>) daoPage.getResult();
		
		List<InvoicePrintPoolHInParam> final_list= new ArrayList<InvoicePrintPoolHInParam>();
		for(Object[] item:resultList){
			InvoicePrintPoolHInParam printPoolHInParam=new InvoicePrintPoolHInParam();
			printPoolHInParam.setInvoicePreNumber((String)item[0]);
			printPoolHInParam.setId((String)item[1]);
			printPoolHInParam.setInvoiceCategory((String)item[2]);
			printPoolHInParam.setInvoiceCode((String)item[3]);
			printPoolHInParam.setInvoiceNumber((String)item[4]);
			printPoolHInParam.setInvoiceReqType((String)item[5]);
			printPoolHInParam.setInvoicePrintStatus((String)item[6]);
			printPoolHInParam.setCustomerNumber((String)item[7]);
			printPoolHInParam.setOrgId((String)item[8]);
			printPoolHInParam.setEquipmentId((String)item[9]);
			printPoolHInParam.setEquipmentCode((String)item[10]);
			printPoolHInParam.setEquipmentName((String)item[11]);
			printPoolHInParam.setCustRegistrationCode((String)item[12]);
			printPoolHInParam.setCustRegistrationNumber((String)item[13]);
			printPoolHInParam.setLegalEntityName((String)item[14]);
			printPoolHInParam.setInvoicePrintBy((String)item[15]);
			printPoolHInParam.setInvoicePrintDate((Date)item[16]);
			printPoolHInParam.setInvoiceReqNumber((String)item[17]);
			printPoolHInParam.setInvoiceAmount((BigDecimal)item[18]);
			printPoolHInParam.setVatAmount((BigDecimal)item[19]);
			printPoolHInParam.setAcctdAmountCR((BigDecimal)item[20]);
			
			final_list.add(printPoolHInParam);
		}
		daoPage.setResult(final_list);
		return daoPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolHPage", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadInvoicePrintPoolHPage(@RequestParam Map<String,Object> parameter) throws Exception {
		DaoPage daoPage=invoicePrintPoolHService.findInvoicePrintPoolHByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		List<InvoicePrintPoolHInParam> result_list= (ArrayList<InvoicePrintPoolHInParam>)daoPage.getResult();
		for(InvoicePrintPoolHInParam inParam:result_list){
			TmsCrvatInvoicePreH temp=(TmsCrvatInvoicePreH)invoicePrintPoolHService.get(TmsCrvatInvoicePreH.class, inParam.getCrvatInvoicePreHId());
			InvoiceReqH invoiceReqH=(InvoiceReqH)invoicePrintPoolHService.get(InvoiceReqH.class,temp.getCrvatInvoiceReqHId());
			inParam.setInvoiceReqNumber(invoiceReqH.getCrvatInvoiceReqNumber());
			inParam.setOrgId(invoiceReqH.getOrgId());
			inParam.setOrgName(OrgCacheUtils.getNodeByOrgId(inParam.getOrgId()).getName());
			inParam.setInvoicePreNumber(temp.getCrvatInvoicePreNumber());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("invoicePrtPoolHId", inParam.getId());
			
			BigDecimal vatAmount=new BigDecimal(0);
			BigDecimal invoiceAmount=new BigDecimal(0);
			BigDecimal acctdAmountCR=new BigDecimal(0);
			List<InvoicePrintPoolLInParam> temp_list=invoicePrintPoolLService.findInvoicePrintPoolLByParams(map);
			
			for(InvoicePrintPoolLInParam invoicePrintPoolLInParam:temp_list){
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getInvoiceAmount())){
					invoiceAmount=invoiceAmount.add(invoicePrintPoolLInParam.getInvoiceAmount());
				}
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getVatAmount())){
					vatAmount=vatAmount.add(invoicePrintPoolLInParam.getVatAmount());
				}
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getAcctdAmountCR())){
					acctdAmountCR=acctdAmountCR.add(invoicePrintPoolLInParam.getAcctdAmountCR());
				}
			}
			inParam.setVatAmount(vatAmount);
			inParam.setInvoiceAmount(invoiceAmount);
			inParam.setAcctdAmountCR(acctdAmountCR);
			convertDictionaryData(inParam);
		}
		return daoPage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintedPoolHPage", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public DaoPage loadInvoicePrintedPoolHPage(@RequestParam Map<String,Object> parameter) throws Exception {
		parameter.put("invoicePrintStatus", InvoicePrintStatusEnums.PRINTED.getValue());
		DaoPage daoPage=invoicePrintPoolHService.findInvoicePrintedPoolHByParams(parameter,PageUtils.getPageNumber(parameter),PageUtils.getPageSize(parameter));
		List<InvoicePrintPoolHInParam> result_list= (ArrayList<InvoicePrintPoolHInParam>)daoPage.getResult();
		for(InvoicePrintPoolHInParam inParam:result_list){
			TmsCrvatInvoicePreH temp=(TmsCrvatInvoicePreH)invoicePrintPoolHService.get(TmsCrvatInvoicePreH.class, inParam.getCrvatInvoicePreHId());
			InvoiceReqH invoiceReqH=(InvoiceReqH)invoicePrintPoolHService.get(InvoiceReqH.class,temp.getCrvatInvoiceReqHId());
			inParam.setInvoiceReqNumber(invoiceReqH.getCrvatInvoiceReqNumber());
			inParam.setInvoicePreNumber(temp.getCrvatInvoicePreNumber());
			inParam.setOrgId(invoiceReqH.getOrgId());
			inParam.setOrgName(OrgCacheUtils.getNodeByOrgId(inParam.getOrgId()).getName());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("invoicePrtPoolHId", inParam.getId());
			
			BigDecimal vatAmount=new BigDecimal(0);
			BigDecimal invoiceAmount=new BigDecimal(0);
			BigDecimal acctdAmountCR=new BigDecimal(0);
			List<InvoicePrintPoolLInParam> temp_list=invoicePrintPoolLService.findInvoicePrintPoolLByParams(map);
			for(InvoicePrintPoolLInParam invoicePrintPoolLInParam:temp_list){
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getInvoiceAmount())){
					invoiceAmount=invoiceAmount.add(invoicePrintPoolLInParam.getInvoiceAmount());
				}
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getVatAmount())){
					vatAmount=vatAmount.add(invoicePrintPoolLInParam.getVatAmount());
				}
				if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getAcctdAmountCR())){
					acctdAmountCR=acctdAmountCR.add(invoicePrintPoolLInParam.getAcctdAmountCR());
				}
			}
			inParam.setVatAmount(vatAmount);
			inParam.setInvoiceAmount(invoiceAmount);
			inParam.setAcctdAmountCR(acctdAmountCR);
			convertDictionaryData(inParam);
		}
		
		return daoPage;
	}
	
	
	/**
	 * 查询明细表信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolL", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public Collection<InvoicePrintPoolLInParam> loadInvoicePrintPoolL(@RequestParam Map<String, Object> map) throws Exception {
		List<InvoicePrintPoolLInParam> result=invoicePrintPoolHService.findInvoicePrintPoolLByParams(map);
		return result;
	}
	
	/**
	 * 开票
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolHSubmitList", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public List<InvoicePrintPoolHInParam> loadInvoicePrintPoolHSubmitList(HttpServletRequest request,@RequestParam(value="id") String parameter) throws Exception {
		AssertHelper.notEmpty_assert(parameter,"发票记录不能为空");
		List<InvoicePrintPoolHInParam> result=new ArrayList<InvoicePrintPoolHInParam>();
		String[] invoicePrintPoolHIds=parameter.split(",");
		for(String invoicePrintPoolHId:invoicePrintPoolHIds){
			if(!invoicePrintPoolHId.equals("")){
				InvoicePrintPoolHInParam inParam_temp=new InvoicePrintPoolHInParam();
				InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class,invoicePrintPoolHId.toString());
				//条件判断
				if(entity.getInvoicePrintStatus().equals(InvoicePrintStatusEnums.TOBEINVOICE.getValue())){
					//开票接口调用
					InvoiceIssueRequest invoiceIssueRequest=new InvoiceIssueRequest();
					AssertHelper.notEmpty_assert(entity.getEquipmentId(), "打印终端为空，请检查！");
					TmsMdEquipment tmsMdEquipment=(TmsMdEquipment)invoicePrintPoolHService.get(TmsMdEquipment.class, entity.getEquipmentId());
					AssertHelper.notEmpty_assert(tmsMdEquipment.getId(), "打印终端为空，请检查！");
					invoiceIssueRequest.setIp(tmsMdEquipment.getEquipmentIp());
					invoiceIssueRequest.setPort(tmsMdEquipment.getEquipmentPort());
					List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
					InvoiceIssue temp_issue=buildInvoiceIssue(entity);
					prints.add(temp_issue);
					invoiceIssueRequest.setRecord(prints);
					taxInterface.processIssueInvoice(invoiceIssueRequest);
				}
				inParam_temp=loadInvoicePrintPoolHInParamDetail(entity);
				if(inParam_temp.getInvoicePrintStatus().equals(InvoicePrintStatusEnums.TOBEPRINT.getValue())){
					inParam_temp.setCheckFlag("1");
				}else {
					inParam_temp.setCheckFlag("0");
				}
				result.add(inParam_temp);
			}
		}
		return result;
	}
	
	/**
	 * 返回完整发票对象(包含头信息以及行信息合并)
	 * @return
	 * @throws Exception
	 */
	protected InvoicePrintPoolHInParam loadInvoicePrintPoolHInParamDetail(InvoicePrintPoolH inParam)throws Exception{
		InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class,inParam.getId());
		TmsCrvatInvoicePreH pre_temp=(TmsCrvatInvoicePreH)invoicePrintPoolHService.get(TmsCrvatInvoicePreH.class, entity.getCrvatInvoicePreHId());
		InvoiceReqH invoiceReqH=(InvoiceReqH)invoicePrintPoolHService.get(InvoiceReqH.class,pre_temp.getCrvatInvoiceReqHId());
		
		InvoicePrintPoolHInParam return_param= new InvoicePrintPoolHInParam();
		ReflectUtils.copyProperties(entity, return_param);
		return_param.setInvoiceReqNumber(invoiceReqH.getCrvatInvoiceReqNumber());
		return_param.setInvoicePreNumber(pre_temp.getCrvatInvoicePreNumber());
		return_param.setOrgId(invoiceReqH.getOrgId());
		Map<String, Object> map=new HashMap<String, Object>();
			map.put("invoicePrtPoolHId", inParam.getId());
		BigDecimal vatAmount=new BigDecimal(0);
		BigDecimal invoiceAmount=new BigDecimal(0);
		BigDecimal acctdAmountCR=new BigDecimal(0);
		List<InvoicePrintPoolLInParam> temp_list=invoicePrintPoolLService.findInvoicePrintPoolLByParams(map);
		return_param.setPrintPoolLInParamList(temp_list);
			
		for(InvoicePrintPoolLInParam invoicePrintPoolLInParam:temp_list){
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getInvoiceAmount())){
				invoiceAmount=invoiceAmount.add(invoicePrintPoolLInParam.getInvoiceAmount());
			}
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getVatAmount())){
				vatAmount=vatAmount.add(invoicePrintPoolLInParam.getVatAmount());
			}
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getAcctdAmountCR())){
				acctdAmountCR=acctdAmountCR.add(invoicePrintPoolLInParam.getAcctdAmountCR());
			}
			
		}
			
		for(InvoicePrintStatusEnums statusEnums:InvoicePrintStatusEnums.values()){
				if(return_param.getInvoicePrintStatus().equals(statusEnums.getValue())){
					return_param.setInvoicePrintStatusName(statusEnums.getText());
					break;
				}
			}
			//发票类型
		for(VatCRInvoiceTypeEnums typeEnums:VatCRInvoiceTypeEnums.values()){
				if(return_param.getInvoiceCategory().equals(typeEnums.getValue())){
					return_param.setInvoiceCategoryName(typeEnums.getText());
					break;
				}
			}
		/**
		 * 来源
		 */
		for(InvoiceReqTypeEnums typeEnums:InvoiceReqTypeEnums.values()){
			if(AssertHelper.notEmpty(return_param.getInvoiceReqType())&&typeEnums.getValue().equals(return_param.getInvoiceReqType().trim())){
				return_param.setInvoiceReqTypeName(getMessage(typeEnums.getText()));
				break;
			}
		}
			return_param.setOrgName(OrgCacheUtils.getNodeByOrgId(return_param.getOrgId()).getName());
			return_param.setVatAmount(vatAmount);
			return_param.setInvoiceAmount(invoiceAmount);
			return_param.setAcctdAmountCR(acctdAmountCR);
		return return_param;
	}
	
	
	/**
	 * 
	 *	发票打印
	 * 功能详细描述
	 * @param parameter
	 * @throws Exception
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolHPrintSubmit", method = RequestMethod.POST)
	//@RoleAnnotation(roles=RoleDef.ECOMMERCE_ADMIN)
	public void loadInvoicePrintPoolHPrintSubmit(@RequestParam(value="id") String parameter) throws Exception {
		AssertHelper.notEmpty_assert(parameter,"发票记录不能为空");
		List<InvoicePrintPoolHInParam> result=new ArrayList<InvoicePrintPoolHInParam>();
		String[] invoicePrintPoolHIds=parameter.split(",");
		for(String invoicePrintPoolHId:invoicePrintPoolHIds){
			if(!invoicePrintPoolHId.equals("")){
				InvoicePrintPoolHInParam inParam_temp=new InvoicePrintPoolHInParam();
				InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class,invoicePrintPoolHId.toString());
				//条件判断
				if(entity.getInvoicePrintStatus().equals(InvoicePrintStatusEnums.TOBEPRINT.getValue())){
					//满足条件时，调用开票接口,根据返回结果状态，更新数据库
					InvoicePrintRequest invoicePrintRequest=new InvoicePrintRequest();
					AssertHelper.notEmpty_assert(entity.getEquipmentId(), "打印终端为空，请检查！");
					TmsMdEquipment tmsMdEquipment=(TmsMdEquipment)invoicePrintPoolHService.get(TmsMdEquipment.class, entity.getEquipmentId());
					AssertHelper.notEmpty_assert(tmsMdEquipment.getId(), "打印终端为空，请检查！");
					invoicePrintRequest.setIp(tmsMdEquipment.getEquipmentIp());
					invoicePrintRequest.setPort(tmsMdEquipment.getEquipmentPort());
					List<InvoicePrint> invoicePrintList=new ArrayList<InvoicePrint>();
					invoicePrintList.add(buildInvoicePrint(entity));
					invoicePrintRequest.setRecords(invoicePrintList);
					taxInterface.processPrintInvoice(invoicePrintRequest);
					
					/*String return_value="1";
					if(return_value.equals("1")){
						//回写开票结果
						entity.setInvoicePrintStatus(InvoicePrintStatusEnums.PRINTED.getValue());
						invoicePrintPoolHService.update(entity);
						//回写发票库存
					}else {
						//inParam_temp.setInvoiceSendResultStatus("发票接口接口调用失败");
						throw new BusinessException("发票打印接口调用失败!");
					}*/
					
				}else {
					throw new BusinessException("发票不具备打印的条件!");
					//inParam_temp.setInvoiceSendResultStatus("发票状态有误");
				}
				
				inParam_temp=loadInvoicePrintPoolHInParamDetail(entity);
				result.add(inParam_temp);
			}
		}
		
	}
	
	/**
	 * 发票详情查询
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadInvoicePrintPoolHDetail", method = RequestMethod.POST)
	public InvoicePrintPoolHInParam loadInvoicePrintPoolHDetail(@RequestParam("invoicePrtPoolHId") String invoicePrtPoolHId) throws Exception {
		AssertHelper.notEmpty_assert(invoicePrtPoolHId,"主键不能为空");
		InvoicePrintPoolH invoicePrintPoolH=new InvoicePrintPoolH();
		invoicePrintPoolH.setId(invoicePrtPoolHId);
		InvoicePrintPoolHInParam resultParam=loadInvoicePrintPoolHInParamDetail(invoicePrintPoolH);
		
		return resultParam;
	}
	
	/**
	 * 发票打印池管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/printPoolHManageInit", method = RequestMethod.GET)
	public String printPoolHManageInit()throws Exception{
		return "vat/invoice/invoicePrintPoolH/printPoolHManageInit";
	}
	/**
	 * 发票打印查询
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/printPoolHQueryInit", method = RequestMethod.GET)
	public String printPoolHQueryInit()throws Exception{
		return "vat/invoice/invoicePrintPoolH/printPoolHQueryInit";
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/invoicePrintLegalEntityCodeList", method = RequestMethod.POST)
	public List<Map<String, Object>> invoicePrintLegalEntityCodeList()throws Exception{
		
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();	
		/*LegalEntityCacheUtils.geTerminalNodesByLegalId()
		for(InvoicePrintStatusEnums eunm:InvoicePrintStatusEnums.values()){
		       String value = eunm.getValue();
		       String text =eunm.getText();
		       String retextString  = getMessage(text);
		       Map<String, Object> map = new HashMap<String, Object>();
		       map.put("value", value);
		       map.put("text",retextString);
		       if(!value.equals("30")){
		    	   results.add(map);
		       }
		 }*/
		return results;
	}
	/**
	 * 获取当前用户打印终端编号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/poolManagePrintStatus", method = RequestMethod.POST)
	public List<Map<String, Object>> poolManagePrintStatus()throws Exception{
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();		 
		for(InvoicePrintStatusEnums eunm:InvoicePrintStatusEnums.values()){
		       String value = eunm.getValue();
		       String text =eunm.getText();
		       String retextString  = getMessage(text);
		       Map<String, Object> map = new HashMap<String, Object>();
		       map.put("value", value);
		       map.put("text",retextString);
		       if(!value.equals("30")){
		    	   results.add(map);
		       }
		 }
		return results;
	}
	
	/**
	 * 获取数据字典
	 * @param parentCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDictionaryEntityByParentCode", method = RequestMethod.POST)
	public Collection<DictionaryEntity> getMethod(@RequestParam("parentCode") String parentCode){
		Collection<DictionaryEntity> results=dictionaryService.loadDictionaryEntities(parentCode);
		return results;
	}
	
	/**
	 * 获取数据字典
	 * @param response
	 * @param parentCode
	 * @throws IOException
	 */
	@RequestMapping(value = "/getDicEntityByParentCode", method = RequestMethod.GET)    
    public void getDictionaryEntitiesByParentCode(HttpServletResponse response,@RequestParam("parentCode") String parentCode) throws IOException{
                  Collection<DictionaryEntity> results = dictionaryService.loadDictionaryEntities(parentCode);
                  List<Map<String, String>> reList = new ArrayList<Map<String,String>>();
                  for(DictionaryEntity dictionaryEntity:results){
                               Map<String,String> map = new HashMap<String,String>();                             
                               map.put("code", dictionaryEntity.getCode());
                               map.put("name", dictionaryEntity.getName());
                               reList.add(map);
                  }            
                  JSONArray jsonArray = JSONArray.fromObject(reList);
                  retJsonArray(response, jsonArray);
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/getInvoicePrintPoolDInParamDetailList", method = RequestMethod.POST)
	public InvoicePrintPoolLInParam getInvoicePrintPoolDInParamDetailList(@RequestParam(value="id") String id) throws Exception {
		AssertHelper.notEmpty_assert(id,"主键不能为空");
		InvoicePrintPoolLInParam resultParam=new InvoicePrintPoolLInParam();
		ReflectUtils.copyProperties(invoicePrintPoolHService.get(InvoicePrintPoolL.class, id.toString()), resultParam);
		Map<String,String> param=new HashMap<String,String>();
		param.put("invoicePrtPoolLId", resultParam.getId());
		resultParam.setInvoicePrintPoolDList(invoicePrintPoolDService.findInvoicePrintPoolDByPoolLId(param));
		return resultParam;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public InvoicePrintPoolHInParam convertDictionaryData(InvoicePrintPoolHInParam model)throws Exception{
		InvoicePrintPoolHInParam return_param=model;
		for(InvoicePrintStatusEnums statusEnums:InvoicePrintStatusEnums.values()){
			if(return_param.getInvoicePrintStatus().equals(statusEnums.getValue())){
				return_param.setInvoicePrintStatusName(statusEnums.getText());
				break;
			}
		}
		//发票类型
		for(VatCRInvoiceTypeEnums typeEnums:VatCRInvoiceTypeEnums.values()){
			if(return_param.getInvoiceCategory().equals(typeEnums.getValue())){
				return_param.setInvoiceCategoryName(typeEnums.getText());
				break;
			}
		}
		//发票来源
		for(InvoiceReqTypeEnums typeEnums:InvoiceReqTypeEnums.values()){
			if(AssertHelper.notEmpty(return_param.getInvoiceReqType())&&typeEnums.getValue().equals(return_param.getInvoiceReqType().trim())){
				return_param.setInvoiceReqTypeName(getMessage(typeEnums.getText()));
				break;
			}
		}
		return return_param;
		
	}
	
	/**
	 * 获取当前用户申请组织列表
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadApplyOrgListForInvoicePrint", method = RequestMethod.GET)
	public void loadApplyOrgListForInvoicePrint(@RequestParam Map<String,Object> parameter, HttpServletResponse response) throws Exception {
		Collection<OrgNode> orgNodes=OrgCacheUtils.getNodeByOrgId(ContextUtils.getCurrentOrgId()).getPosterities().values();
		//Collection<LeafNode> orgNode=PrintOrgCacheUtils.getPrintNodeByOrgId(ContextUtils.getCurrentOrgId()).getPosterities().values();
		List<InvoicePrintApplyOrgInParam> return_list= new ArrayList<InvoicePrintApplyOrgInParam>();
		for(OrgNode param:orgNodes){
			InvoicePrintApplyOrgInParam model=new InvoicePrintApplyOrgInParam();
			model.setOrgCode(param.getCode());
			model.setOrgId(param.getId());
			model.setOrgName(param.getName());
			return_list.add(model);
		}
		JSONArray result = new JSONArray();
		result = JSONArray.fromObject(return_list);
		retJsonArray(response, result);
	}
	
	/**
	 * 获取打印终端列表
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/loadLegalEntityForInvoicePrint", method = RequestMethod.POST)
	public void loadLegalEntityForInvoicePrint(@RequestParam Map<String,Object> parameter, HttpServletResponse response) throws Exception {
		Object id=parameter.get("id");
		AssertHelper.notEmpty_assert(id, "发票ID为空,请检查");
		InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class, id.toString());
		AssertHelper.notEmpty_assert(entity.getLegalEntityId(), "纳税主体ID为空,请检查");
		
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("legalEntityId", entity.getLegalEntityId());
		List<Object[]> list=legalEquipmentService.getEquipMentListByParams(params);
		
		JSONArray result = new JSONArray();
		List<InvoicePrintLegalEntityInParam> return_list= new ArrayList<InvoicePrintLegalEntityInParam>();
		
		for(Object[] obj : list){
			TmsMdEquipment tmsMdEquipment = (TmsMdEquipment)obj[1];
			InvoicePrintLegalEntityInParam inParam=new InvoicePrintLegalEntityInParam();
			inParam.setEquipmentCode(tmsMdEquipment.getEquipmentCode());
			inParam.setEquipmentName(tmsMdEquipment.getEquipmentName());
			inParam.setEquipmentId(tmsMdEquipment.getId());
			inParam.setEquipmentIp(tmsMdEquipment.getEquipmentIp());
			inParam.setEquipmentPort(tmsMdEquipment.getEquipmentPort());
			return_list.add(inParam);
		}
		
		result = JSONArray.fromObject(return_list);
		retJsonArray(response, result);
	}
	/**
	 * 发票打印终端更新
	 * @param parameter
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/saveLegalEntityForInvoicePrint", method = RequestMethod.POST)
	public InvoicePrintPoolHInParam saveLegalEntityForInvoicePrint(@RequestParam Map<String,Object> parameter, HttpServletResponse response) throws Exception {
		InvoicePrintPoolHInParam inParam =new InvoicePrintPoolHInParam();
		Object id=parameter.get("id");
		Object equipmentCode=parameter.get("equipmentCode");
		Object equipmentName=parameter.get("equipmentName");
		Object equipmentId=parameter.get("equipmentId");
		
		AssertHelper.notEmpty_assert(id,"发票记录不能为空,更新打印终端失败");
		AssertHelper.notEmpty_assert(equipmentCode,"打印终端编号不能为空,更新打印终端失败");
		AssertHelper.notEmpty_assert(equipmentName,"打印终端名称不能为空,更新打印终端失败");
		InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class, id.toString());
		
		if(entity.getInvoicePrintStatus().equals(InvoicePrintStatusEnums.TOBEINVOICE.getValue())){
			entity.setEquipmentCode(equipmentCode.toString());
			entity.setEquipmentName(equipmentName.toString());
			entity.setEquipmentId(equipmentId.toString());
			invoicePrintPoolHService.update(entity);
		}else{
			throw new BusinessException("该发票不能再更新打印终端");
		}
		inParam=invoicePrintPoolHService.convertInvoicePrintPoolHToInParam(entity);
		return inParam;
	}
	/**
	 * 产生一张发票请求
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected InvoiceIssue buildInvoiceIssue(InvoicePrintPoolH model)throws Exception{
		InvoiceIssue invoiceIssue=new InvoiceIssue();
		InvoicePrintPoolH current = (InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class, model.getId());
		//头信息封装
		invoiceIssue.setKey(current.getId());
		InvoiceIssueHead invoiceHead=new InvoiceIssueHead();
		if(current.getInvoiceCategory().equals(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_ZP.getValue())){//专票
			invoiceHead.setInvoiceType(FPZLEnums.zp.getValue());//发票种类 字符  2 是 //0 专票 2普票 11货运票 12机动车票
		}else if(current.getInvoiceCategory().equals(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_PP.getValue())){
			invoiceHead.setInvoiceType(FPZLEnums.pp.getValue());//发票种类 字符  2 是 //0 专票 2普票 11货运票 12机动车票
		}
		
		invoiceHead.setDrawer(ContextUtils.getCurrentUserName());//开票人 字符 8 是 8个字节，4个汉字
		invoiceHead.setPurchaserName(current.getCustomerName());//购方名称 字符 100  是
		invoiceHead.setPurchaserTaxNo(current.getCustRegistrationNumber());//购方税号 字符 20 是
		invoiceHead.setPurchaserTel(current.getCustRegistrationAddress()+" "+current.getCustContactPhone());//购方地址电话 字符 100  否 
		invoiceHead.setPurchaserBankNo(current.getCustDepositBankAccountNum());//购方银行账户 字符 100  否
		//invoiceHead.setBark("1111");//备注 字符 230  否 
		invoiceHead.setReciver(current.getLegalEntityName());//收款人 字符 8 是 8个字节，4个汉字 //默认为当前
		invoiceHead.setChecker(ContextUtils.getCurrentUserName());//复核人 字符 8 是 8个字节，4个汉字 //默认为当前用户
		invoiceHead.setSalesBankNo(current.getBankAccountNum());//销方银行账户 字符 100  否 
		invoiceHead.setSalesBankTel(current.getRegistrationContactAddress()+" "+current.getRegistrationContactPhone());//销方地址电话  字符 100  否
		invoiceHead.setIsDetail(QDBZEnums.open.getValue());//QDBZ  清单标志 字符 2 是 固定值 0：不开具清单 1：开具清单
		//invoiceHead.setXsdjbh("1234567");// XSDJBH 销售单据编号 字符  100  否 
		invoiceHead.setInvoiceMethod(KPBZEnums.open.getValue());// KPBZ 开票标志 字符 2 是 固定值 0：开票 1：校验 2：空白作废下一张
		invoiceIssue.setHead(invoiceHead);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("invoicePrtPoolHId", current.getId());
		BigDecimal vatAmount=new BigDecimal(0);
		BigDecimal invoiceAmount=new BigDecimal(0);
		BigDecimal acctdAmountCR=new BigDecimal(0);
		List<InvoicePrintPoolLInParam> temp_list=invoicePrintPoolLService.findInvoicePrintPoolLByParams(map);
		for(InvoicePrintPoolLInParam invoicePrintPoolLInParam:temp_list){
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getInvoiceAmount())){
				invoiceAmount=invoiceAmount.add(invoicePrintPoolLInParam.getInvoiceAmount());
			}
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getVatAmount())){
				vatAmount=vatAmount.add(invoicePrintPoolLInParam.getVatAmount());
			}
			if(AssertHelper.notEmpty(invoicePrintPoolLInParam.getAcctdAmountCR())){
				acctdAmountCR=acctdAmountCR.add(invoicePrintPoolLInParam.getAcctdAmountCR());
			}
		}
		
		List<InvoiceIssueDetail> detail_list=new ArrayList<InvoiceIssueDetail>();
		for(InvoicePrintPoolLInParam inParam:temp_list){
			InvoiceIssueDetail detail=new InvoiceIssueDetail();
	         detail.setCommodityName(inParam.getInventoryItemDescripton());//SPMC 商品名称  字符 92 是
	         //
	         detail.setIsTax(HSBZEnums.exclude.getValue());//HSBZ  含税标志 字符 2 是 固定值 0：不含税 1：含税------------
	         if(!AssertHelper.empty(inParam.getTaxRate())){
	        	 detail.setTaxRate(inParam.getTaxRate().doubleValue());//SLV 税率 数值 10,6 是 
	         }
	         if(!AssertHelper.empty(inParam.getInvoiceAmount())){
	        	 detail.setInvoiceAmount(inParam.getInvoiceAmount().doubleValue());//JE  金额 数值 16,2 是 
	         }
	         if(!AssertHelper.empty(inParam.getPriceOfUnit())){
	        	 detail.setUnitPrice(inParam.getPriceOfUnit().doubleValue());//DJ 单价 数值 36,15  否 ------------------
	         }
	         detail.setMeasurementUnit(inParam.getUomCodeDescripton());//JLDW  计量单位  字符 22 否
	         detail.setSpecification(inParam.getInventoryItemModels());//GGXH  规格型号  字符 40 否 
	         if(!AssertHelper.empty(inParam.getVatAmount())){
	        	 detail.setTaxAmount(inParam.getVatAmount().doubleValue());//SE 税额 数值 16,2 是 
	         }
	         if(!AssertHelper.empty(inParam.getInventoryItemQty())){
	        	 detail.setQuantity(inParam.getInventoryItemQty().longValue());//SL  数量 数值 36,15  否
	         }
	         detail_list.add(detail);
	         //商品单价、数量和金额不符合计算关系
	         //商品单价、数量和金额不符合计算关系
	         //购买方纳税人识别号长度不正确三证合一后 6-20位
		}
		invoiceIssue.setDetails(detail_list);
		
		return invoiceIssue;
	}
	/**
	 * 产生一个发票打印请求
	 * @param model
	 * @return
	 * @throws Exception
	 */
	protected InvoicePrint buildInvoicePrint(InvoicePrintPoolH model)throws Exception{
		InvoicePrint invoicePrint=new InvoicePrint();
		InvoicePrintPoolH current = (InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class, model.getId());
		invoicePrint.setKey(current.getId());
		invoicePrint.setInvoiceCode(current.getInvoiceCode());
		invoicePrint.setInvoiceNo(current.getInvoiceNumber());
		//发票类型需要转换
		if(current.getInvoiceCategory().equals(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_ZP.getValue())){//专票
			invoicePrint.setInvoiceType(FPZLEnums.zp.getValue());//发票种类 字符  2 是 //0 专票 2普票 11货运票 12机动车票
		}else if(current.getInvoiceCategory().equals(VatCRInvoiceTypeEnums.VAT_CR_INVOICE_PP.getValue())){
			invoicePrint.setInvoiceType(FPZLEnums.pp.getValue());//发票种类 字符  2 是 //0 专票 2普票 11货运票 12机动车票
		}else {
			invoicePrint.setInvoiceType(FPZLEnums.zp.getValue());
			//throw new BusinessException("发票种类不匹配");
		}
		
		return invoicePrint;
	}
}

