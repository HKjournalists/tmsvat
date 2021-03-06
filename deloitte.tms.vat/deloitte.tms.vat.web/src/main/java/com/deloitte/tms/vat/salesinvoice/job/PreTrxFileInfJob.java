/**    
 * Copyright (C),Deloitte
 * @FileName: PreTmsCrvatTrxInfJobController.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.job  
 * @Description: //文件处理job
 * @Author stonshi  
 * @Date 2016年3月21日 下午9:10:47  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.DataUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TrxFileProcessJobTask;

/**
 * 文件处理job 针对开票流水，从银行各系统导入VAT系统的接口表中，主要包括以下几个： 1.核心销项开票流水 2.信用卡销项开票流水 3.托管销项开票流水
 * 4.SAP销项开票流水 核心销项开票流水 核心系统 增值税管理系统 "文件名:BT+NCBS+yyyymmdd+01，其中BT表明此文件是开票流水文件
 * yyyymmdd是T日" 以16进制字符“0x03”分割 信用卡销项开票流水 信用卡中心 增值税管理系统
 * "文件名:BT+XACP+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日" 以16进制字符“0x03”分割
 * 托管销项开票流水 托管系统 增值税管理系统 "文件名:BT+CMIS+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日"
 * 以16进制字符“0x03”分割 SAP销项开票流水 SAP 增值税管理系统
 * "文件名:BT+SAPS+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日" 以16进制字符“0x03”分割
 * 
 * @author stonshi
 * @create 2016年3月21日 下午9:10:47
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("preTrxFileInfJob")
public class PreTrxFileInfJob implements Job, JobTest {
	
	private final static Logger log = Logger.getLogger(PreTrxFileInfJob.class);

	@Resource
	private TrxFileProcessJobTask fileProcessJob;

	//输入文件
	@Value("${ling2.fileInPath}")
	String fileInPath;

	// 输出文件
	@Value("${ling2.fileOutPath}")
	String fileOutPath;

	@Value("${ling2.trigger}")
	String trigger;
	
	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;
	
	@Resource
	private TmsCrvatTrxInfService tmsCrvatTrxInfService;

	private int allCount;

	private int convertErrCount;

	private int processErrCount;

	StringBuffer sbError = new StringBuffer();

	StringBuffer errInfo = new StringBuffer();

	@Override
	public void execute() {
		String fileType = StringPool.BT;
		List<String> toRunFileName = JobFileUtils.scanAvabileFiles(fileInPath, fileType, trigger);
		if (CollectionUtils.isEmpty(toRunFileName)) {
			throw new BusinessException("没有找到生成的文件");
		}
		for (String toRun : toRunFileName) {
			String path = fileInPath + toRun;
			executeProcessFile(path, fileOutPath);
		}
		
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

	/**
	 * @see 详细参考说明参考父方法
	 */
	public int executeProcessFile(String filePath, String fileOutPath) {

		File file = new File(filePath);
		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}

		Map<String, String> mapFile = new HashMap<String, String>();
		mapFile.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完文件
		mapFile.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		mapFile.put("filePath", filePath);
		mapFile.put("fileOutPath", fileOutPath);
		int postnum = processTmsCrvatTrxInf(mapFile, file);
		
		int postFileErrCount = processAllFiles(mapFile);

//		if (validateResult(postnum-totalsucess, postFileErrCount, convertErrCount, processErrCount)) {
//			throw new BusinessException(sbError.toString());
//			//System.out.print(sbError.toString());
//		}

		return allCount;
	}

	/**
	 * 〈一句话功能简述〉 功能详细描述
	 * 
	 * @param saveErrCount
	 * @param postFileErrCount
	 * @param convertErrCount2
	 * @param processErrCount2
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private boolean validateResult(int saveErrCount, int postFileErrCount, int convertErrCount2, int processErrCount2) {

		int i = 0;
		sbError.append("一共有" + allCount + "条数据，");

		if (saveErrCount > 0) {
			sbError.append(saveErrCount).append("笔数据保存失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (postFileErrCount > 0) {
			sbError.append(postFileErrCount).append("笔数文件后续处理失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (convertErrCount > 0) {
			sbError.append(convertErrCount).append("笔数据转换失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (processErrCount > 0) {
			sbError.append(processErrCount).append("笔数据转换失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 处理前端传来的文件
	 * 
	 * @param mapFile
	 * @param file
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private int processTmsCrvatTrxInf(Map<String, String> mapFile, File file) {
		log.info("*******************beg read preTrxFileInfJob file ******************** ");
		Long totalsapstart = System.currentTimeMillis();
		
		int postnum=0;
		int errocount=0;
		int totalsucess=0;
		
		int pageIndex=0;
		int pageSize=2000;
		
		String lineTxt = null;// 一行字符串定义
		InputStreamReader read = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		TmsCrvatTrxInf tmsCrvatTrxInf = new TmsCrvatTrxInf();
		List<TmsCrvatTrxInf> batchTmsCrvatTrxInfs = new ArrayList<TmsCrvatTrxInf>();
		try {
			read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			br = new BufferedReader(read);
			fw = new FileWriter(new File(mapFile.get("doneFilePath")));
			bw = new BufferedWriter(fw);

			while ((lineTxt = br.readLine()) != null) {
				postnum++;
				tmsCrvatTrxInf = convertToEntity(lineTxt);
				if (tmsCrvatTrxInf != null) {
					tmsCrvatTrxInf.setInterfaceTrxDate(new Date());// 接口处理日期
					tmsCrvatTrxInf.setInterfaceTrxFlag(StringPool.READY);// 接口处理标记
					tmsCrvatTrxInf.setInterfaceTrxMsg(mapFile.get("doneFilePath") + "_" + allCount);// 接口处理信息存放处理文件名+第几批
					if(pageIndex<pageSize){
						batchTmsCrvatTrxInfs.add(tmsCrvatTrxInf);
						pageIndex++;
					}else{
						batchTmsCrvatTrxInfs.add(tmsCrvatTrxInf);
						int sucessnum = fileProcessJob.processSaveTmsCrvatTrxInf(batchTmsCrvatTrxInfs);
						//重置计数器
						totalsucess=totalsucess+sucessnum;
						batchTmsCrvatTrxInfs=new ArrayList<TmsCrvatTrxInf>();					
						pageIndex=0;
					}
				}
				bw.append(lineTxt + "\n");
			}
			//末尾数据处理
			int sucessnum = fileProcessJob.processSaveTmsCrvatTrxInf(batchTmsCrvatTrxInfs);
			totalsucess=totalsucess+sucessnum;
			
			FileUtils.safeClose(bw);
			FileUtils.safeClose(fw);
			FileUtils.safeClose(br);
			FileUtils.safeClose(read);
		} catch (Exception e) {
			errocount++;
			log.error("process preTrxFileInfJob line erro:"+lineTxt+ "erro info:"+e.getMessage());
			e.printStackTrace();
		}
		//处理合计输出
		log.info("preTrxFileInfJob postnum num :"+postnum);
		log.info("sucess process preTrxFileInfJob:"+totalsucess);
		log.info("fail process preTrxFileInfJob:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("*******************end read preTrxFileInfJob file ******************** ");
		return postnum;		
	}
	
//	public int processItem(){
//		int totalnum=allTmsCrvatTrxInfs.size();
//		
//		/*****************************开始批量处理用户数据**************************/
//		int pageIndex=0;
//		int pageSize=2000;
//		int totalsucess=0;
//		List<TmsCrvatTrxInf> batchTmsCrvatTrxInfs=new ArrayList<TmsCrvatTrxInf>();
//		Long totalsapstart = System.currentTimeMillis();		
//		for (TmsCrvatTrxInf trxinf : allTmsCrvatTrxInfs) {
//			if(pageIndex<pageSize){
//				batchTmsCrvatTrxInfs.add(trxinf);
//				pageIndex++;
//			}else{
//				batchTmsCrvatTrxInfs.add(trxinf);
//				int sucessnum = fileProcessJob.processSaveTmsCrvatTrxInf(batchTmsCrvatTrxInfs);
//				//重置计数器
//				totalsucess=totalsucess+sucessnum;
//				batchTmsCrvatTrxInfs=new ArrayList<TmsCrvatTrxInf>();					
//				pageIndex=0;
//			}
//		}
//		//末尾数据处理
//		int sucessnum = fileProcessJob.processSaveTmsCrvatTrxInf(batchTmsCrvatTrxInfs);
//		
//		totalsucess=totalsucess+sucessnum;
//		//处理合计输出
//		log.info("PreTrxFileInfJob "+totalnum+" data costs:："
//				+ (System.currentTimeMillis() - totalsapstart) + " ms"
//				+" sucess:"+totalsucess+"fail:"+(totalnum-totalsucess));
//		/***************************** end 批量处理用户数据**************************/
//		
//		int postFileErrCount = processAllFiles(mapFile);
//
//		if (validateResult(totalnum-totalsucess, postFileErrCount, convertErrCount, processErrCount)) {
//			throw new BusinessException(sbError.toString());
//			//System.out.print(sbError.toString());
//		}
//	}
	/**
	 * 处理后续文件
	 * 
	 * @param mapFile
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private int processAllFiles(Map<String, String> mapFile) {

		int postFileErrCount = 0;

		String doneFilePath = mapFile.get("doneFilePath");
		String fileOutPath = mapFile.get("fileOutPath");
		String filePath = mapFile.get("filePath");
		String trigFilePath = mapFile.get("trigFilePath");
		try {
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.deleteFile(doneFilePath);
			FileUtils.deleteFile(trigFilePath);
		} catch (IOException e) {
			errInfo.append(e.getMessage());
			errInfo.append("\n");
			postFileErrCount++;
		}
		return postFileErrCount;
	}

	/**
	 * 装载数据到TmsCrvatTrxInf实体
	 * 
	 * @param lineTxt
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private TmsCrvatTrxInf convertToEntity(String lineTxt) {

		TmsCrvatTrxInf tmsCrvatTrxInf = new TmsCrvatTrxInf();;

		try {
			String[] splitLineTxt = lineTxt.split("", -1);
			tmsCrvatTrxInf.setOrgCode(StringUtils.trim(splitLineTxt[0])); // 机构号
			tmsCrvatTrxInf.setOrgName(StringUtils.trim(splitLineTxt[0])); // 机构名称
			tmsCrvatTrxInf.setSourceCode(StringUtils.trim(splitLineTxt[1])); // 来源系统
			tmsCrvatTrxInf.setTrxBatchNum(StringUtils.trim(splitLineTxt[2])); // 交易批次号
			tmsCrvatTrxInf.setTrxNumber(StringUtils.trim(splitLineTxt[3])); // 交易流水号
			tmsCrvatTrxInf.setTrxDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[4])));// 交易日期
			Double oriCurrencyAmount = Double.valueOf(StringUtils.trim(splitLineTxt[5]));// 交易金额
			if (oriCurrencyAmount < 0) {
				tmsCrvatTrxInf.setOriginalCurrencyAmount(-1 * oriCurrencyAmount);
			} else {
				tmsCrvatTrxInf.setOriginalCurrencyAmount(oriCurrencyAmount);
			}
			tmsCrvatTrxInf.setOriginalCurrencyCode(StringUtils.trim(splitLineTxt[6]));// 原币币种
			tmsCrvatTrxInf.setCurrencyCode(StringUtils.trim(splitLineTxt[7]));// 本位币
			Double currencyAmount = Double.valueOf(StringUtils.trim(splitLineTxt[8]));// 本位币交易金额
			if (currencyAmount < 0) {
				tmsCrvatTrxInf.setCurrencyAmount(-1 * currencyAmount);
			} else {
				tmsCrvatTrxInf.setCurrencyAmount(currencyAmount);
			}
			tmsCrvatTrxInf.setExchangeRate(Double.valueOf(StringUtils.trim(splitLineTxt[9])));// 开票汇率
			tmsCrvatTrxInf.setRateDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[10])));// 汇率日期
			tmsCrvatTrxInf.setTaxRate(Double.valueOf(StringUtils.trim(splitLineTxt[11])));// 税率
			tmsCrvatTrxInf.setInvoiceCategory(StringUtils.trim(splitLineTxt[12])); // 交易开票规则
			tmsCrvatTrxInf.setDeptId(StringUtils.trim(splitLineTxt[13])); // 部门
			tmsCrvatTrxInf.setInventoryItemNumber(DataUtils.getNumeric(StringUtils.trim(splitLineTxt[14]))); // 商品及服务分类编码货物或应税劳务名称
			tmsCrvatTrxInf.setInventoryItemDescripton(StringUtils.trim(splitLineTxt[14])); // 商品及服务分类编码货物或应税劳务名称
			tmsCrvatTrxInf.setInventoryItemModels(StringUtils.trim(splitLineTxt[15]));
			tmsCrvatTrxInf.setUomCode(StringUtils.trim(splitLineTxt[16]));
			tmsCrvatTrxInf.setInventoryItemQty(Integer.valueOf(StringUtils.trim(splitLineTxt[17]))); // 数量
			tmsCrvatTrxInf.setPriceOfUnit(Double.valueOf(StringUtils.trim(splitLineTxt[18])));// 单价
			tmsCrvatTrxInf.setCustomerAccount(StringUtils.trim(splitLineTxt[19]));// 客户账号
			int i=20;
			tmsCrvatTrxInf.setCustBankOrgCode(StringUtils.trim(splitLineTxt[i++]));//客户账号开户机构号
			tmsCrvatTrxInf.setCustomerNumber(StringUtils.trim(splitLineTxt[i++])); // 客户号
			tmsCrvatTrxInf.setCustomerName(StringUtils.trim(splitLineTxt[i++]));// 客户名称
			tmsCrvatTrxInf.setCustomerType(StringUtils.trim(splitLineTxt[i++]));// 客户类型
			tmsCrvatTrxInf.setLegalEntityType(StringUtils.trim(splitLineTxt[i++]));// 纳税人类型
			tmsCrvatTrxInf.setRegistrationNumber(StringUtils.trim(splitLineTxt[i++]));// 纳税人识别号
			tmsCrvatTrxInf.setCustBankBranchName(StringUtils.trim(splitLineTxt[i++]));// 纳税人开户行名称
			tmsCrvatTrxInf.setCustBankAccountNum(StringUtils.trim(splitLineTxt[i++]));// 纳税人账号
			tmsCrvatTrxInf.setCustRegistrationAddress(StringUtils.trim(splitLineTxt[i++]));// 纳税人地址
			tmsCrvatTrxInf.setCustContactPhone(StringUtils.trim(splitLineTxt[i++]));// 纳税人联系电话
			tmsCrvatTrxInf.setPrintPeriodName(StringUtils.trim(splitLineTxt[i++]));// 打印频率
			tmsCrvatTrxInf.setInvoicingType(StringUtils.trim(splitLineTxt[i++]));// 开票方式
			tmsCrvatTrxInf.setRecipientName(StringUtils.trim(splitLineTxt[i++]));// 收件人姓名
			tmsCrvatTrxInf.setRecipientComp(StringUtils.trim(splitLineTxt[i++]));// 收件人公司
			tmsCrvatTrxInf.setRecipientAddr(StringUtils.trim(splitLineTxt[i++]));// 收件人地址
			tmsCrvatTrxInf.setRecipientPhone(StringUtils.trim(splitLineTxt[i++]));// 收件人电话
			tmsCrvatTrxInf.setAutoreverseFlag(StringUtils.trim(splitLineTxt[i++]));// 明细冲账标识
			tmsCrvatTrxInf.setReverseTypeCode(StringUtils.trim(splitLineTxt[i++]));// 冲账类型
			tmsCrvatTrxInf.setReversalDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[i++])));// 被冲/冲账交易日期
			tmsCrvatTrxInf.setReversalTrxNumber(StringUtils.trim(splitLineTxt[i++]));// 冲账交易日志号
			tmsCrvatTrxInf.setAppointInvoiceOrgCode(StringUtils.trim(splitLineTxt[i++]));// 预约开票管理机构
			tmsCrvatTrxInf.setAttribute1(StringUtils.trim(splitLineTxt[i++]));
			tmsCrvatTrxInf.setAttribute2(StringUtils.trim(splitLineTxt[i++]));
			tmsCrvatTrxInf.setIsAccount("Y");
			return tmsCrvatTrxInf;
		} catch (Exception e) {
			errInfo.append(e.getMessage());
			errInfo.append("\n");
			convertErrCount++;
			return null;
		}
		
	}
}
