package com.deloitte.tms.pl.business.model;

import com.deloitte.tms.pl.business.service.rule.param.IParameter;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * TblBusinessParams generated by hbm2java
 */
public class BusinessParams  extends BaseEntity implements IParameter{


    private BusinessParamsset businessParamsset;
	/**参数名称参数名称*/
    private String paramcode;
	/**参数值参数值*/
    private String paramvalue;
	/**命名命名*/
    private String label;
	/**描述描述*/
    private String des;
	/***/
    private Integer sortOrder;
	/**参数类型参数类型*/
    private String type;
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	public BusinessParamsset getBusinessParamsset() {
		return businessParamsset;
	}
	public void setBusinessParamsset(BusinessParamsset businessParamsset) {
		this.businessParamsset = businessParamsset;
	}
	public String getParamcode() {
		return paramcode;
	}
	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}


