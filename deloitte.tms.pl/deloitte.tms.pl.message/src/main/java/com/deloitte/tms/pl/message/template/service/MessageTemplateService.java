package com.deloitte.tms.pl.message.template.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.message.template.model.MessageParameter;
import com.deloitte.tms.pl.message.template.model.MessageParameterInParam;
import com.deloitte.tms.pl.message.template.model.MessageTemplate;
import com.deloitte.tms.pl.message.template.model.MessageTemplateInParam;
import com.deloitte.tms.pl.system.model.ByteContent;
/**
 * Home object for domain model class MessageTemplate.
 * @see com.deloitte.tms.pl.message.template.model
 * @author Hibernate Tools
 */
public interface MessageTemplateService extends IService{
	public static final String BEAN_ID="messageTemplateService";
	public static final String GROUPID_CONTENT="M_CONTENT";
	
	public DaoPage findMessageTemplateByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<MessageTemplateInParam> findMessageTemplateByParams(Map params);
	
	public void saveMessageTemplateDataListsMap(Map dataListsMap);
	
	public MessageTemplateInParam convertMessageTemplateToInParam(MessageTemplate model);
	
	public MessageTemplate convertMessageTemplateInParamToEntity(MessageTemplateInParam inParam);
	
	public DaoPage findMessageParameterByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<MessageParameterInParam> findMessageParameterByParams(Map params);
	
	public void saveMessageParameterDataListsMap(Map dataListsMap);
	
	public MessageParameterInParam convertMessageParameterToInParam(MessageParameter model);
	
	public MessageParameter convertMessageParameterInParamToEntity(MessageParameterInParam inParam);
	
	public ByteContent findByteContent(Map params);
	
	public ByteContent findByteContent(Long messageTemplateId);
}

