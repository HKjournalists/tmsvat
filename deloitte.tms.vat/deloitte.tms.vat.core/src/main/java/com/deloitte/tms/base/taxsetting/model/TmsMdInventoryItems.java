package com.deloitte.tms.base.taxsetting.model;

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
 * TmsMdInventoryItems generated by ling autoproject
 */
@Entity
@Table(name=TmsMdInventoryItems.TABLE)
@ModelProperty(comment="商品及服务编码")
public class TmsMdInventoryItems extends BaseEntity {

	public static final String TABLE = "TMS_MD_INVENTORY_ITEMS";
	public static final String SEQ = TABLE;

    @Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")

    @Column(name="INVENTORY_ITEM_ID")
	@ModelProperty(comment="商品及服务编码ID")
	private String id;

    @Column(name="ORG_ID")
	@ModelProperty(comment="组织ID")
	private String orgId;
    
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="ORG_ID",insertable=false,updatable=false,nullable=true)
	private BaseOrg baseOrg;
	
    @Column(name="INVENTORY_CATEGORY_ID")
	@ModelProperty(comment="商品及服务分类ID")
	private String inventoryCategoryId;
    
	public BaseOrg getBaseOrg() {
		return baseOrg;
	}

	public void setBaseOrg(BaseOrg baseOrg) {
		this.baseOrg = baseOrg;
	}

	public TmsMdInventoryCategories getTmsMdInventoryCategories() {
		return tmsMdInventoryCategories;
	}

	public void setTmsMdInventoryCategories(
			TmsMdInventoryCategories tmsMdInventoryCategories) {
		this.tmsMdInventoryCategories = tmsMdInventoryCategories;
	}

	public Items getTaxItems() {
		return taxItems;
	}

	public void setTaxItems(Items taxItems) {
		this.taxItems = taxItems;
	}

	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="INVENTORY_CATEGORY_ID",insertable=false,updatable=false,nullable=true)
	private TmsMdInventoryCategories tmsMdInventoryCategories;



    @Column(name="INVENTORY_ITEM_NUMBER")
	@ModelProperty(comment="商品及服务编码")
	private String inventoryItemNumber;

    @Column(name="INVENTORY_ITEM_DESCRIPTON")
	@ModelProperty(comment="商品及服务名称")
	private String inventoryItemDescripton;

    @Column(name="INVENTORY_ITEM_MODELS")
	@ModelProperty(comment="规格型号")
	private String inventoryItemModels;

    @Column(name="UOM_CODE")
	@ModelProperty(comment="单位代码_枚举值")
	private String uomCode;

    @Column(name="INVENTORY_CATEGORY_CODE")
	@ModelProperty(comment="客户商品及服务分类")
	private String inventoryCategoryCode;

    @Column(name="INVENTORY_ITEM_TYPE")
	@ModelProperty(comment="属性(国标/自定义)")
	private String inventoryItemType;

    @Column(name="TAX_ITEM_ID")
	@ModelProperty(comment="税目ID")
	private String taxItemId;
    
	@ManyToOne
	@Cascade(CascadeType.REFRESH)
	@JoinColumn(name="TAX_ITEM_ID",insertable=false,updatable=false,nullable=true)
	private Items taxItems;

    @Column(name="TAX_RATE")
	@ModelProperty(comment="税率")
	private Double taxRate;

    @Column(name="ENABLED_FLAG")
	@ModelProperty(comment="是否有效")
	private Boolean enabledFlag;

    @Column(name="IS_LOCK")
	@ModelProperty(comment="是否锁定")
	private Boolean isLock;

    @Column(name="START_DATE")
	@ModelProperty(comment="有效日期")
	private Date startDate;

    @Column(name="END_DATE")
	@ModelProperty(comment="失效日期")
	private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getInventoryCategoryId() {
		return inventoryCategoryId;
	}

	public void setInventoryCategoryId(String inventoryCategoryId) {
		this.inventoryCategoryId = inventoryCategoryId;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}

	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}

	public String getInventoryItemModels() {
		return inventoryItemModels;
	}

	public void setInventoryItemModels(String inventoryItemModels) {
		this.inventoryItemModels = inventoryItemModels;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getInventoryCategoryCode() {
		return inventoryCategoryCode;
	}

	public void setInventoryCategoryCode(String inventoryCategoryCode) {
		this.inventoryCategoryCode = inventoryCategoryCode;
	}

	public String getInventoryItemType() {
		return inventoryItemType;
	}

	public void setInventoryItemType(String inventoryItemType) {
		this.inventoryItemType = inventoryItemType;
	}

	public String getTaxItemId() {
		return taxItemId;
	}

	public void setTaxItemId(String taxItemId) {
		this.taxItemId = taxItemId;
	}

	public Boolean getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Boolean enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
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

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	
    
}


