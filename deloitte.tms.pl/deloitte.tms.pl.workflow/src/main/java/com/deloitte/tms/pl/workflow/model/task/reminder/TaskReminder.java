package com.deloitte.tms.pl.workflow.model.task.reminder;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
@Entity
@Table(name=TablePreDef.WORKFLOW+"TASK_REMINDER")
public class TaskReminder {
	@Id
	@Column(name="ID_")
	private long id;
	
	@Column(name="TASK_ID_")
	private long taskId;
	
	@Column(name="PROCESS_ID_")
	private long processId;
	
	@Column(name="CRON_",length=60)
	private String cron;
	
	@Column(name="TASK_NODE_NAME_",length=60)
	private String taskNodeName;
	
	@Column(name="REMINDER_HANDLER_BEAN_",length=120)
	private String reminderHandlerBean;
	
	@Column(name="REMINDER_TYPE_",length=20)
	@Enumerated(EnumType.STRING)
	private ReminderType type;
	
	@Column(name="START_DATE_")
	private Date startDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	public String getTaskNodeName() {
		return taskNodeName;
	}
	public void setTaskNodeName(String taskNodeName) {
		this.taskNodeName = taskNodeName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public ReminderType getType() {
		return type;
	}
	public void setType(ReminderType type) {
		this.type = type;
	}
	public String getReminderHandlerBean() {
		return reminderHandlerBean;
	}
	public void setReminderHandlerBean(String reminderHandlerBean) {
		this.reminderHandlerBean = reminderHandlerBean;
	}
}
