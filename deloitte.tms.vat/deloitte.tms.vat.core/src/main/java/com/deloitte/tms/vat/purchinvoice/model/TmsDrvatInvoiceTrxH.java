package com.deloitte.tms.vat.purchinvoice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * TmsDrvatInvoiceTrxH generated by ling autoproject
 */
@Entity
@Table(name=TmsDrvatInvoiceTrxH.TABLE)
@ModelProperty(comment="进项税发票登记-主表")
public class TmsDrvatInvoiceTrxH extends BaseEntity {

	public static final String TABLE = "TMS_DRVAT_INVOICE_TRX_H";
	public static final String SEQ = TABLE;

    @Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")

    @Column(name="INVOICE_TRX_H_ID")
	@ModelProperty(comment="进项税发票登记ID")
	private String id;

    @Column(name="INVOICE_BUSINESS_CATEGORY")
	@ModelProperty(comment="进项税业务发票类型_枚举值")
	private String invoiceBusinessCategory;

    @Column(name="INVOICE_CATEGORY")
	@ModelProperty(comment="进项税发票类型_枚举值")
	private String invoiceCategory;

    @Column(name="INVOICE_TRX_BATCH_NUMBER")
	@ModelProperty(comment="进项税发票批次")
	private String invoiceTrxBatchNumber;

    @Column(name="DESCRIPTION")
	@ModelProperty(comment="描述")
	private String description;

    @Column(name="REQUESTION_BY")
	@ModelProperty(comment="申请人")
	private String requestionBy;

    @Column(name="REQUESTION_DEPT")
	@ModelProperty(comment="申请部门")
	private String requestionDept;

    @Column(name="END_REQUESTION_DATE")
	@ModelProperty(comment="申请结束时间")
	private Date endRequestionDate;

    @Column(name="START_REQUESTION_DATE")
	@ModelProperty(comment="申请开始时间")
	private Date startRequestionDate;

    @Column(name="LEGAL_ENTITY_ID")
	@ModelProperty(comment="纳税人实体ID")
	private String legalEntityId;

    @Column(name="ORG_ID")
	@ModelProperty(comment="组织ID")
	private String orgId;

    @Column(name="SOURCE_CODE")
	@ModelProperty(comment="发票来源")
	private String sourceCode;

    @Column(name="APPROVAL_DATE")
	@ModelProperty(comment="审批流日期")
	private Date approvalDate;

    @Column(name="APPROVAL_BY")
	@ModelProperty(comment="审批人")
	private String approvalBy;

    @Column(name="APPROVAL_ORG_ID")
	@ModelProperty(comment="审批部门")
	private String approvalOrgId;

    @Column(name="APPROVAL_STATUS")
	@ModelProperty(comment="审批流状态")
	private String approvalStatus;

    @Column(name="WF_TASK_ID")
	@ModelProperty(comment="工作流ID")
	private String wfTaskId;

    @Column(name="APPROVAL_DESC")
	@ModelProperty(comment="审批流状态描述")
	private String approvalDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceBusinessCategory() {
		return invoiceBusinessCategory;
	}

	public void setInvoiceBusinessCategory(String invoiceBusinessCategory) {
		this.invoiceBusinessCategory = invoiceBusinessCategory;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}

	public String getInvoiceTrxBatchNumber() {
		return invoiceTrxBatchNumber;
	}

	public void setInvoiceTrxBatchNumber(String invoiceTrxBatchNumber) {
		this.invoiceTrxBatchNumber = invoiceTrxBatchNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequestionBy() {
		return requestionBy;
	}

	public void setRequestionBy(String requestionBy) {
		this.requestionBy = requestionBy;
	}

	public String getRequestionDept() {
		return requestionDept;
	}

	public void setRequestionDept(String requestionDept) {
		this.requestionDept = requestionDept;
	}

	public Date getEndRequestionDate() {
		return endRequestionDate;
	}

	public void setEndRequestionDate(Date endRequestionDate) {
		this.endRequestionDate = endRequestionDate;
	}

	public Date getStartRequestionDate() {
		return startRequestionDate;
	}

	public void setStartRequestionDate(Date startRequestionDate) {
		this.startRequestionDate = startRequestionDate;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getApprovalBy() {
		return approvalBy;
	}

	public void setApprovalBy(String approvalBy) {
		this.approvalBy = approvalBy;
	}

	public String getApprovalOrgId() {
		return approvalOrgId;
	}

	public void setApprovalOrgId(String approvalOrgId) {
		this.approvalOrgId = approvalOrgId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getWfTaskId() {
		return wfTaskId;
	}

	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

    
}


