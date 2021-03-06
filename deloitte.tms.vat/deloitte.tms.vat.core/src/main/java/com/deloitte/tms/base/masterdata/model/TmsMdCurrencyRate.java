package com.deloitte.tms.base.masterdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * TmsMdCurrencyRate generated by ling autoproject
 */
@Entity
@Table(name=TmsMdCurrencyRate.TABLE)
@ModelProperty(comment="汇率定义")
public class TmsMdCurrencyRate extends BaseEntity {

	public static final String TABLE = "TMS_MD_CURRENCY_RATE";
	public static final String SEQ = TABLE;

    @Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
    @Column(name="CURRENCY_RATE_ID")
	@ModelProperty(comment="汇率ID")
	private String id;

    @Column(name="SOURCE_CURRENCY_CODE")
	@ModelProperty(comment="源币种_枚举值")
	private String sourceCurrencyCode;

    @Column(name="TARGET_CURRENCY_CODE")
	@ModelProperty(comment="目标币种_枚举值")
	private String targetCurrencyCode;

    @Column(name="CURRENCY_RATE")
	@ModelProperty(comment="汇率")
	private Double  currencyRate;


	@Column(name="EXCHANGE_DATE")
	@ModelProperty(comment="汇率日期")
	private Date exchangeDate;

    @Column(name="DESCRIPTION")
	@ModelProperty(comment="描述")
	private String description;
	
    @Column(name="ORG_ID")
	@ModelProperty(comment="组织ID")
	private String orgId;   
    
    @ManyToOne
  	@Cascade(CascadeType.REFRESH)
  	@JoinColumn(name="ORG_ID", insertable=false,updatable=false,nullable=true)
    private BaseOrg baseOrg;
    
    
    
    
    public BaseOrg getBaseOrg() {
		return baseOrg;
	}

	public void setBaseOrg(BaseOrg baseOrg) {
		this.baseOrg = baseOrg;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	    // Property accessors
	public String getCurrencyRateId() {
	    return this.id;
	}
	
	public void setCurrencyRateId(String currencyRateId) {
	    this.id = currencyRateId;
	}
	public String getSourceCurrencyCode() {
	    return this.sourceCurrencyCode;
	}
	
	public void setSourceCurrencyCode(String sourceCurrencyCode) {
	    this.sourceCurrencyCode = sourceCurrencyCode;
	}
	public String getTargetCurrencyCode() {
	    return this.targetCurrencyCode;
	}
	
	public void setTargetCurrencyCode(String targetCurrencyCode) {
	    this.targetCurrencyCode = targetCurrencyCode;
	}
	public Double getCurrencyRate() {
	    return this.currencyRate;
	}
	
	public void setCurrencyRate(Double currencyRate) {
	    this.currencyRate = currencyRate;
	}
	public Date getExchangeDate() {
	    return this.exchangeDate;
	}
	
	public void setExchangeDate(Date exchangeDate) {
	    this.exchangeDate = exchangeDate;
	}
	public String getDescription() {
	    return this.description;
	}
	
	public void setDescription(String description) {
	    this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}


