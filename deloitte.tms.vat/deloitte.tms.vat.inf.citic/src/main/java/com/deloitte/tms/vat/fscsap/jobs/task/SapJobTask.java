
/**    
 * Copyright (C),Deloitte
 * @FileName: FileProcessingService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service  
 * @Description: //模块目的、功能描述  
 * @author tigchen  
 * @Date 2016年3月21日 下午8:06:01  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.fscsap.jobs.task;

/**  
 * 用户信息文件处理
 * @author stonshi
 * @create 2016年3月21日 下午8:06:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SapJobTask {
	
	public static final String BEAN_ID = "sapJobTask";

	/**
	 * 处理文件
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int executeProcessFile(String filePath, String fileOutPath);
}
