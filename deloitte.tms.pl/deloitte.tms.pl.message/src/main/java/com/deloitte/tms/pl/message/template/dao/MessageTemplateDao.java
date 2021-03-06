package com.deloitte.tms.pl.message.template.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.message.template.model.MessageParameter;
import com.deloitte.tms.pl.message.template.model.MessageTemplate;
import com.deloitte.tms.pl.system.model.ByteContent;
/**
 * Home object for domain model class MessageTemplate.
 * @see com.deloitte.tms.pl.message.template.model
 * @author Hibernate Tools
 */
public interface MessageTemplateDao extends IDao<MessageTemplate>{
	public static final String BEAN_ID="messageTemplateDao";
	public DaoPage findMessageTemplateByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<MessageTemplate> findMessageTemplateByParams(Map params);
	public DaoPage findMessageParameterByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<MessageParameter> findMessageParameterByParams(Map params);
	public ByteContent findByteContent(Map params);
}

