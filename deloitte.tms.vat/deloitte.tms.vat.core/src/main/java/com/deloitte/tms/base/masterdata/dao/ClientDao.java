package com.deloitte.tms.base.masterdata.dao;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.Client;
import com.deloitte.tms.base.masterdata.model.ClientSec;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
/**
 * Home object for domain model class Client.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
public interface ClientDao extends IDao<Client>{
	public static final String BEAN_ID="clientDao";
	public DaoPage findClientByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<Client> findClientByParams(Map params);
	public DaoPage findClientSecByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<ClientSec> findClientSecByParams(Map params);
}

