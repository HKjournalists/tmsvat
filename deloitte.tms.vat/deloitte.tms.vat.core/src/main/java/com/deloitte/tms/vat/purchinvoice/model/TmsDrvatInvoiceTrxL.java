package com.deloitte.tms.vat.purchinvoice.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.core.common.ExcelProcess;

/**
 * TmsDrvatInvoiceTrxL generated by ling autoproject
 */
@Entity
@Table(name=TmsDrvatInvoiceTrxL.TABLE)
@ModelProperty(comment="进项税发票登记-行表")
public class TmsDrvatInvoiceTrxL extends BaseEntity {
	

	public static final String TABLE = "TMS_DRVAT_INVOICE_TRX_L";
	public static final String SEQ = TABLE;

    @Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")

    @Column(name="INVOICE_TRX_L_ID")
	@ModelProperty(comment="发票批次ID")
	private String id;

    @Column(name="INVOICE_TRX_H_ID")
	@ModelProperty(comment="进项税发票登记ID")
	private String invoiceTrxHId;

    @Column(name="INVOICE_CODE")
	@ModelProperty(comment="发票代码")
	private String invoiceCode;

    @Column(name="INVOICE_NUMBER")
	@ModelProperty(comment="发票编码")
	private String invoiceNumber;

    @Column(name="INVOICING_DATE")
	@ModelProperty(comment="发票日期")
	private Date invoicingDate;

    @Column(name="ENTERED_AMOUNT")
	@ModelProperty(comment="净额")  //有可能是小数， 不能用Long， 必须用Double支持小数点的类型。。。
	private Double enteredAmount;

    @Column(name="VAT_AMOUNT")
	@ModelProperty(comment="税额")
	private Double vatAmount;

    @Column(name="VENDER_ENTITY_ID")
	@ModelProperty(comment="销方纳税主体ID")
	private String venderEntityId;

    @Column(name="VENDER_REGISTRATION_NUMBER")
	@ModelProperty(comment="销方纳税人识别号")
	private String venderRegistrationNumber;

    @Column(name="VENDER_NUMBER")
	@ModelProperty(comment="销货单位编码")
	private String venderNumber;

    @Column(name="VENDER_NAME")
	@ModelProperty(comment="销货单位名称")
	private String venderName;

    @Column(name="VENDER_CONTACT_ADDRESS")
	@ModelProperty(comment="销方地址")
	private String venderContactAddress;

    @Column(name="VENDER_CONTACT_PHONE")
	@ModelProperty(comment="销方电话")
	private String venderContactPhone;

    @Column(name="VENDER_BANK_NAME")
	@ModelProperty(comment="开户行")
	private String venderBankName;

    @Column(name="VENDER_BANK_ACCOUNT_NUM")
	@ModelProperty(comment="银行账户")
	private String venderBankAccountNum;

    @Column(name="LEGAL_ENTITY_ID")
	@ModelProperty(comment="纳税人实体ID")
	private String legalEntityId;

    @Column(name="CONTRACT_ID")
	@ModelProperty(comment="合同ID")
	private String contractId;

    @Column(name="CONTRACT_CODE")
	@ModelProperty(comment="合同编码")
	private String contractCode;

    @Column(name="CONTRACT_NAME")
	@ModelProperty(comment="合同名称")
	private String contractName;

    @Column(name="CHARGE_NUMBER")
	@ModelProperty(comment="报账单号")
	private String chargeNumber;

    @Column(name="CHARGE_STATUS")
	@ModelProperty(comment="报账单状态")
	private String chargeStatus;

    @Column(name="INVOICE_AUTHENTICATION_STATUS")
	@ModelProperty(comment="发票认证状态")
	private String invoiceAuthenticationStatus;

    @Column(name="SIGN_STATUS")
	@ModelProperty(comment="签收状态")
	private String signStatus;

    @Column(name="SIGN_DATE")
	@ModelProperty(comment="签收时间")
	private Date signDate;
    
	@Column(name = "ATTRIBUTE6",  length = 240)
	@ModelProperty(comment="ATTRIBUTE6")
	String attribute6;//含税金额
	

	@Column(name = "ATTRIBUTE7",  length = 240)
	@ModelProperty(comment="ATTRIBUTE7")
	String attribute7;//税金
	
	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceTrxHId() {
		return invoiceTrxHId;
	}

	public void setInvoiceTrxHId(String invoiceTrxHId) {
		this.invoiceTrxHId = invoiceTrxHId;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoicingDate() {
		return invoicingDate;
	}

	public void setInvoicingDate(Date invoicingDate) {
		this.invoicingDate = invoicingDate;
	}

	public Double getEnteredAmount() {
		return enteredAmount;
	}

	public void setEnteredAmount(Double enteredAmount) {
		this.enteredAmount = enteredAmount;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getVenderEntityId() {
		return venderEntityId;
	}

	public void setVenderEntityId(String venderEntityId) {
		this.venderEntityId = venderEntityId;
	}

	public String getVenderRegistrationNumber() {
		return venderRegistrationNumber;
	}

	public void setVenderRegistrationNumber(String venderRegistrationNumber) {
		this.venderRegistrationNumber = venderRegistrationNumber;
	}

	public String getVenderNumber() {
		return venderNumber;
	}

	public void setVenderNumber(String venderNumber) {
		this.venderNumber = venderNumber;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getVenderContactAddress() {
		return venderContactAddress;
	}

	public void setVenderContactAddress(String venderContactAddress) {
		this.venderContactAddress = venderContactAddress;
	}

	public String getVenderContactPhone() {
		return venderContactPhone;
	}

	public void setVenderContactPhone(String venderContactPhone) {
		this.venderContactPhone = venderContactPhone;
	}

	public String getVenderBankName() {
		return venderBankName;
	}

	public void setVenderBankName(String venderBankName) {
		this.venderBankName = venderBankName;
	}

	public String getVenderBankAccountNum() {
		return venderBankAccountNum;
	}

	public void setVenderBankAccountNum(String venderBankAccountNum) {
		this.venderBankAccountNum = venderBankAccountNum;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getChargeNumber() {
		return chargeNumber;
	}

	public void setChargeNumber(String chargeNumber) {
		this.chargeNumber = chargeNumber;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getInvoiceAuthenticationStatus() {
		return invoiceAuthenticationStatus;
	}

	public void setInvoiceAuthenticationStatus(String invoiceAuthenticationStatus) {
		this.invoiceAuthenticationStatus = invoiceAuthenticationStatus;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

    
	public TmsDrvatInvoiceTrxL() {
		super();
	}
	
	
	public TmsDrvatInvoiceTrxL(String[] importExcelData) {
		super();
		/**
		 *  static {
	 // excelHeaderFields.add("发票系统"); attribute2     / 不是导入的
	   ATTRIBUTE1     --> excelHeaderFields.add("验证日期");   attribute1      / Date  --0
	INVOICE_CODE-->  excelHeaderFields.add("发票代号");  invoiceCode         ---1
	invoice_number-->  excelHeaderFields.add("发票票号");   invoiceNumber
	vender_registration_number-->  excelHeaderFields.add("纳税人识别号"); venderRegistrationNumber
	INVOICING_DATE-->  excelHeaderFields.add("开票日期");    invoicingDate   /  Date
	  
attribute6-->	--5  excelHeaderFields.add("含税金额 ->改成 净额");  attribute6-->    vatAmount + enteredAmount(净额)
vat_Amount-> --6	  excelHeaderFields.add("税金");           vatAmount
vernder_name-->	  excelHeaderFields.add("纳税人名称");         venderName
	  excelHeaderFields.add("公司代码");             attribute3
	  excelHeaderFields.add("发票验证人");           attribute4  
	  
	  excelHeaderFields.add("发票状态");           invoiceAuthenticationStatus  / 不是导入的 ->20160419变成是导入初始值PF
	  
	  
	 // list.add("认证失败原因");                  attribute5 / 不是导入的
  }
		 */
		//setAttribute1setInvoiceCode

		try {
			
		Integer len = importExcelData.length;
		ArrayList<String> fields =  ExcelProcess.excelHeaderFieldsByDb;
		
		
		System.out.println("actual read record line has fields:"+len + "; and defined record should have fields:"+fields.size());
		
		int mixLen = len <= fields.size() ? len : fields.size();
		
		for(int i=0; i< mixLen; i++){
			
			StringBuffer sb = new StringBuffer();
			Date tempDate=new Date();
			if(i==4){ 
				
				//from excel it's Date?
				//invoicingDate 开票日期 Date
				//tempDate=ExcelProcess.getDateByStr(importExcelData[i]);
			}
			
			String field = fields.get(i);
			sb.append("set");
			sb.append(field.subSequence(0, 1).toString().toUpperCase());
			sb.append(field.subSequence(1, field.length()).toString());
			
			
			
			if(i==4){
				Method m = TmsDrvatInvoiceTrxL.class.getMethod(sb.toString(), Date.class);
				System.out.println("--------orginal:"+ importExcelData[i]);
				Date d = new Date(  importExcelData[i] );
				System.out.println("--------after:"+ d);
				//m.invoke(this, tempDate);
				m.invoke(this, d);
			}else if(i==100){//attribute7', title:'税额'
				
				/*Method m = TmsDrvatInvoiceTrxL.class.getMethod(sb.toString(),Double.class);
				
				m.invoke(this, Double.parseDouble( importExcelData[i])   );
				*/
			}else{
				Method m = TmsDrvatInvoiceTrxL.class.getMethod(sb.toString(), String.class);
				m.invoke(this, importExcelData[i]);	
			}
			
		}
			}  catch(Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}

	
	public TmsDrvatInvoiceTrxL(String id){
		super();
		this.id=id;
		this.setFlag(LittleUtils.one);
	}
	
	
}


