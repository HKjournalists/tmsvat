package com.deloitte.tms.base.taxsetting.model;

import java.io.Serializable;
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

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * TmsMdBsnCombination generated by ling autoproject
 */
@Entity
@Table(name=TmsMdBsnCombination.TABLE)
@ModelProperty(comment="业务数据组合")
public class TmsMdBsnCombination extends BaseEntity {

	public static final String TABLE = "TMS_MD_BSN_COMBINATION";
	public static final String SEQ = TABLE;

    @Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")

    @Column(name="BSN_COMBINATION_ID", length=36)
	@ModelProperty(comment="业务数据组合ID")
	private String id;

    @Column(name="BSN_COMBINATION_CODE", length=2000)
	@ModelProperty(comment="业务数据组合代码")
	private String bsnCombinationCode;

    @Column(name="BSN_COMBINATION_NAME", length=2000)
	@ModelProperty(comment="业务数据组合说明")
	private String bsnCombinationName;

    @Column(name="FLEX_STRUCTURES_ID", length=36)
	@ModelProperty(comment="数据结构定义ID(业务)")
	private String flexStructuresId;

    @Column(name="TRANS_SEGMENT1", length=100)
	@ModelProperty(comment="业务字段1")
	private String transSegment1;

    @Column(name="TRANS_SEGMENT2", length=100)
	@ModelProperty(comment="业务字段2")
	private String transSegment2;

    @Column(name="TRANS_SEGMENT3", length=100)
	@ModelProperty(comment="业务字段3")
	private String transSegment3;

    @Column(name="TRANS_SEGMENT4", length=100)
	@ModelProperty(comment="业务字段4")
	private String transSegment4;

    @Column(name="TRANS_SEGMENT5", length=100)
	@ModelProperty(comment="业务字段5")
	private String transSegment5;

    @Column(name="TRANS_SEGMENT6", length=100)
	@ModelProperty(comment="业务字段6")
	private String transSegment6;

    @Column(name="TRANS_SEGMENT7", length=100)
	@ModelProperty(comment="业务字段7")
	private String transSegment7;

    @Column(name="TRANS_SEGMENT8", length=100)
	@ModelProperty(comment="业务字段8")
	private String transSegment8;

    @Column(name="TRANS_SEGMENT9", length=100)
	@ModelProperty(comment="业务字段9")
	private String transSegment9;

    @Column(name="TRANS_SEGMENT10", length=100)
	@ModelProperty(comment="业务字段10")
	private String transSegment10;

    @Column(name="LEGAL_ENTITY_ID", length=36)
	@ModelProperty(comment="纳税主体ID")
	private String legalEntityId;

    @Column(name="ENABLED_FLAG", length=10)
	@ModelProperty(comment="是否启用(Y/N)")
	private String enabledFlag;

    @Column(name="DESCRIPTION", length=500)
	@ModelProperty(comment="备注")
	private String description;

    @Column(name="ORG_ID", length=36)
	@ModelProperty(comment="组织ID")
	private String orgId;

    @Column(name="START_DATE", length=7)
	@ModelProperty(comment="有效日期")
	private Date startDate;

    @Column(name="END_DATE", length=7)
	@ModelProperty(comment="失效日期")
	private Date endDate;
    
    
    
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBsnCombinationCode() {
		return bsnCombinationCode;
	}

	public void setBsnCombinationCode(String bsnCombinationCode) {
		this.bsnCombinationCode = bsnCombinationCode;
	}

	public String getBsnCombinationName() {
		return bsnCombinationName;
	}

	public void setBsnCombinationName(String bsnCombinationName) {
		this.bsnCombinationName = bsnCombinationName;
	}

	public String getFlexStructuresId() {
		return flexStructuresId;
	}

	public void setFlexStructuresId(String flexStructuresId) {
		this.flexStructuresId = flexStructuresId;
	}

	public String getTransSegment1() {
		return transSegment1;
	}

	public void setTransSegment1(String transSegment1) {
		this.transSegment1 = transSegment1;
	}

	public String getTransSegment2() {
		return transSegment2;
	}

	public void setTransSegment2(String transSegment2) {
		this.transSegment2 = transSegment2;
	}

	public String getTransSegment3() {
		return transSegment3;
	}

	public void setTransSegment3(String transSegment3) {
		this.transSegment3 = transSegment3;
	}

	public String getTransSegment4() {
		return transSegment4;
	}

	public void setTransSegment4(String transSegment4) {
		this.transSegment4 = transSegment4;
	}

	public String getTransSegment5() {
		return transSegment5;
	}

	public void setTransSegment5(String transSegment5) {
		this.transSegment5 = transSegment5;
	}

	public String getTransSegment6() {
		return transSegment6;
	}

	public void setTransSegment6(String transSegment6) {
		this.transSegment6 = transSegment6;
	}

	public String getTransSegment7() {
		return transSegment7;
	}

	public void setTransSegment7(String transSegment7) {
		this.transSegment7 = transSegment7;
	}

	public String getTransSegment8() {
		return transSegment8;
	}

	public void setTransSegment8(String transSegment8) {
		this.transSegment8 = transSegment8;
	}

	public String getTransSegment9() {
		return transSegment9;
	}

	public void setTransSegment9(String transSegment9) {
		this.transSegment9 = transSegment9;
	}

	public String getTransSegment10() {
		return transSegment10;
	}

	public void setTransSegment10(String transSegment10) {
		this.transSegment10 = transSegment10;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    
}


