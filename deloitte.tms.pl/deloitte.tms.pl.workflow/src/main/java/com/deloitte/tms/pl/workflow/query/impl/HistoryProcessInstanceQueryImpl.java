package com.deloitte.tms.pl.workflow.query.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.CommandService;
import com.deloitte.tms.pl.workflow.command.impl.QueryCountCommand;
import com.deloitte.tms.pl.workflow.command.impl.QueryListCommand;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
import com.deloitte.tms.pl.workflow.query.HistoryProcessInstanceQuery;
import com.deloitte.tms.pl.workflow.query.QueryJob;

/**
 * @author Jacky.gao
 * @since 2013年8月14日
 */
public class HistoryProcessInstanceQueryImpl implements HistoryProcessInstanceQuery,QueryJob {
	private long processId;
	private int firstResult;
	private int maxResults;
	private String tag;
	private String promoter;
	private String businessId;
	private Date createDateLessThen;
	private Date createDateLessThenOrEquals;
	private Date createDateGreaterThen;
	private Date createDateGreaterThenOrEquals;
	private List<String> ascOrders=new ArrayList<String>();
	private List<String> descOrders=new ArrayList<String>();
	private CommandService commandService;
	public HistoryProcessInstanceQueryImpl(CommandService commandService){
		this.commandService=commandService;
	}
	
	public Criteria getCriteria(Session session,boolean queryCount) {
		Criteria criteria=session.createCriteria(HistoryProcessInstance.class);
		buildCriteria(criteria,queryCount);						
		return criteria;
	}
	
	public List<HistoryProcessInstance> list(){
		return commandService.executeCommand(new QueryListCommand<List<HistoryProcessInstance>>(this));
	}
	
	public int count(){
		return commandService.executeCommand(new QueryCountCommand(this));		
	}
	
	private void buildCriteria(Criteria criteria,boolean queryCount){
		if(!queryCount && firstResult>0){
			criteria.setFirstResult(firstResult);			
		}
		if(!queryCount && maxResults>0){
			criteria.setMaxResults(maxResults);			
		}
		criteria.add(Restrictions.isNotNull("endDate"));
		if(processId>0){
			criteria.add(Restrictions.eq("processId", processId));
		}
		if(StringUtils.isNotEmpty(businessId)){
			criteria.add(Restrictions.eq("businessId", businessId));
		}
		if(StringUtils.isNotEmpty(tag)){
			criteria.add(Restrictions.eq("tag", tag));
		}
		if(StringUtils.isNotEmpty(promoter)){
			criteria.add(Restrictions.eq("promoter", promoter));
		}
		if(createDateLessThen!=null){
			criteria.add(Restrictions.lt("createDate", createDateLessThen));
		}
		if(createDateGreaterThen!=null){
			criteria.add(Restrictions.gt("createDate", createDateGreaterThen));
		}
		if(createDateLessThenOrEquals!=null){
			criteria.add(Restrictions.le("createDate", createDateLessThenOrEquals));
		}
		if(createDateGreaterThenOrEquals!=null){
			criteria.add(Restrictions.ge("createDate", createDateGreaterThenOrEquals));
		}
		if(!queryCount){
			for(String ascProperty:ascOrders){
				criteria.addOrder(Order.asc(ascProperty));
			}
			for(String descProperty:descOrders){
				criteria.addOrder(Order.desc(descProperty));
			}
		}
	}
	

	public HistoryProcessInstanceQuery processId(long processId){
		this.processId=processId;
		return this;
	}

	public HistoryProcessInstanceQuery page(int firstResult, int maxResults){
		this.firstResult=firstResult;
		this.maxResults=maxResults;
		return this;
	}

	public HistoryProcessInstanceQuery addOrderAsc(String property){
		ascOrders.add(property);
		return this;
	}
	public HistoryProcessInstanceQuery businessId(String businessId){
		this.businessId=businessId;
		return this;
	}
	public HistoryProcessInstanceQuery promoter(String promoter){
		this.promoter=promoter;
		return this;
	}
	public HistoryProcessInstanceQuery tag(String tag){
		this.tag=tag;
		return this;
	}
	public HistoryProcessInstanceQuery addOrderDesc(String property){
		descOrders.add(property);
		return this;
	}

	public HistoryProcessInstanceQuery createDateLessThen(Date date){
		this.createDateLessThen=date;
		return this;
	}

	public HistoryProcessInstanceQuery createDateLessThenOrEquals(Date date){
		this.createDateLessThenOrEquals=date;
		return this;
	}

	public HistoryProcessInstanceQuery createDateGreaterThen(Date date){
		this.createDateGreaterThen=date;
		return this;
	}

	public HistoryProcessInstanceQuery createDateGreaterThenOrEquals(Date date){
		this.createDateGreaterThenOrEquals=date;
		return this;
	}
}
