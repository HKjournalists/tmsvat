package com.deloitte.tms.vat.salesinvoice.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntityInParam;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceAllocDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceTrxDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAllocD;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAllocH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAllocHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAllocL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceAllocLInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxD;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxL;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceAllocService;
/**
 * Home object for domain model class InvoiceAlloc.
 * @see com.deloitte.tms.vat.salesinvoice.model
 * @author Hibernate Tools
 */
@Component(InvoiceAllocService.BEAN_ID)
public class InvoiceAllocServiceImpl extends BaseService implements InvoiceAllocService{
	@Resource
	InvoiceAllocDao invoiceAllocDao;

	@Resource
	InvoiceTrxDao invoiceTrxDao;
	
	@Override
	public IDao getDao() {
		return invoiceAllocDao;
	}

	@Override
	public List<InvoiceAllocH> getLatestInvoiceAllocH() {
		List<InvoiceAllocH> list = invoiceAllocDao.getLatestInvoiceAllocH();
		return list;
	}
	
	@Override
	public DaoPage findInvoiceAllocByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceAllocDao.findInvoiceAllocByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceAllocToInParam((List<InvoiceAllocH>) daoPage.getResult()));
		return daoPage;
	}

	@Override
	public DaoPage findInvoiceAllocLByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceAllocDao.findInvoiceAllocLByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceAllocLToInParam((List<InvoiceAllocL>) daoPage.getResult()));
		return daoPage;
	}
	
	@Override
	public DaoPage findEquipmentForPage(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceAllocDao.findEquipmentForPage(params, pageIndex, pageSize);
		return daoPage;
	}
	
	@Override
	public List<InvoiceAllocHInParam> findInvoiceAllocByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<InvoiceAllocH> temp= invoiceAllocDao.findInvoiceAllocByParams(params);
		return convertInvoiceAllocToInParam(temp);
	}
	
	@Override
	public List<TmsMdLegalEntity> loadLegalEntityForEditPanel(Map params) {
		List<TmsMdLegalEntity> temp= invoiceAllocDao.loadLegalEntityForEditPanel(params);
		return temp;
	}
	
	@Override
	public List<TmsMdEquipment> loadEquipmentForEditPanel(Map params) {
		List<TmsMdEquipment> temp= invoiceAllocDao.loadEquipmentForEditPanel(params);
		return temp;
	}
	@Override
	public DaoPage findLegalEntityByParams(Map params, Integer pageIndex, Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceAllocDao.findLegalEntityByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertLegalEntityToInParam((List<TmsMdLegalEntity>) daoPage.getResult()));
		return daoPage;
	}
	
	private List<TmsMdLegalEntityInParam> convertLegalEntityToInParam(List<TmsMdLegalEntity> models){
		List<TmsMdLegalEntityInParam> result=new ArrayList<TmsMdLegalEntityInParam>();
		for(TmsMdLegalEntity initiation:models){
			TmsMdLegalEntityInParam inparam=convertLegalEntityToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	
	public TmsMdLegalEntityInParam convertLegalEntityToInParam(TmsMdLegalEntity model){
		TmsMdLegalEntityInParam inparam=new TmsMdLegalEntityInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	@Override
	public void saveInvoiceAllocDataListsMap(Map dataListsMap) {
		Collection<InvoiceAllocHInParam> deleteList = BatchUtils.to(InvoiceAllocHInParam.class).getDeleteEntities(dataListsMap);
		Collection<InvoiceAllocHInParam> insertList =  BatchUtils.to(InvoiceAllocHInParam.class).getInsertEntities(dataListsMap);
		Collection<InvoiceAllocHInParam> updateList =  BatchUtils.to(InvoiceAllocHInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(InvoiceAllocHInParam inParam: updateList){
				InvoiceAllocH entity=convertInvoiceAllocInParamToEntity(inParam);
				invoiceAllocDao.update(entity);
			}		
//			invoiceAllocDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(InvoiceAllocHInParam inParam: insertList){
				InvoiceAllocH entity=convertInvoiceAllocInParamToEntity(inParam);
				invoiceAllocDao.save(entity);
				inParam.setId(entity.getAttribute2());
			}
//			invoiceAllocDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(InvoiceAllocHInParam inParam:deleteList){
				InvoiceAllocH entity=new InvoiceAllocH();
				entity.setId(inParam.getAttribute2());
				invoiceAllocDao.remove(entity);
			}
//			invoiceAllocDao.removeAll(deleteList);
		}
	}
	
	
	@Override
	public void saveInvoiceAllocLDataListsMap(Map dataListsMap) {
		Collection<InvoiceAllocLInParam> deleteList = BatchUtils.to(InvoiceAllocLInParam.class).getDeleteEntities(dataListsMap);
		Collection<InvoiceAllocLInParam> insertList =  BatchUtils.to(InvoiceAllocLInParam.class).getInsertEntities(dataListsMap);
		Collection<InvoiceAllocLInParam> updateList =  BatchUtils.to(InvoiceAllocLInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(InvoiceAllocLInParam inParam: updateList){
				InvoiceAllocL entity=convertInvoiceAllocLInParamToEntity(inParam);
				invoiceAllocDao.update(entity);
			}		
//			invoiceAllocDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(InvoiceAllocLInParam inParam: insertList){
				InvoiceAllocL entity=convertInvoiceAllocLInParamToEntity(inParam);
				invoiceAllocDao.save(entity);
				inParam.setId(entity.getAttribute2());
			}
//			invoiceAllocDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(InvoiceAllocLInParam inParam:deleteList){
				InvoiceAllocL entity=new InvoiceAllocL();
				entity.setId(inParam.getAttribute2());
				invoiceAllocDao.remove(entity);
			}
//			invoiceAllocDao.removeAll(deleteList);
		}
		
	}
	private List<InvoiceAllocHInParam> convertInvoiceAllocToInParam(List<InvoiceAllocH> models){
		List<InvoiceAllocHInParam> result=new ArrayList<InvoiceAllocHInParam>();
		for(InvoiceAllocH initiation:models){
			InvoiceAllocHInParam inparam=convertInvoiceAllocToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}

	public InvoiceAllocH getInvoiceAllocH(String id){
		InvoiceAllocH allocH = (InvoiceAllocH) invoiceAllocDao.get(InvoiceAllocH.class, id);
		Hibernate.initialize(allocH.getInvoiceAllocLs());		
		return allocH;
	}
	
	private List<InvoiceAllocLInParam> convertInvoiceAllocLToInParam(List<InvoiceAllocL> models){
		List<InvoiceAllocLInParam> result=new ArrayList<InvoiceAllocLInParam>();
		for(InvoiceAllocL initiation:models){
			InvoiceAllocLInParam inparam=convertInvoiceAllocLToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	
	public InvoiceAllocHInParam convertInvoiceAllocToInParam(InvoiceAllocH model){
		InvoiceAllocHInParam inparam=new InvoiceAllocHInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public InvoiceAllocLInParam convertInvoiceAllocLToInParam(InvoiceAllocL model){
		InvoiceAllocLInParam inparam=new InvoiceAllocLInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public InvoiceAllocH convertInvoiceAllocInParamToEntity(InvoiceAllocHInParam inParam){
		InvoiceAllocH entity=new InvoiceAllocH();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
	public InvoiceAllocL convertInvoiceAllocLInParamToEntity(InvoiceAllocLInParam inParam){
		InvoiceAllocL entity=new InvoiceAllocL();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
	
	@Override
	public void saveInvoiceAlloc(InvoiceAllocH invoiceAllocH) {
		// TODO Auto-generated method stub
		for(InvoiceAllocL invoiceAllocL:invoiceAllocH.getList()){
			invoiceAllocDao.save(invoiceAllocL);
			
			for(int j=0;j<invoiceAllocL.getInvoiceAllotQty();j++){
				String invoiceNumber = String.format("%08d", (Integer.parseInt(invoiceAllocL.getStartInvoiceNumber())+j));
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("invoiceCode", invoiceAllocL.getInvoiceCode());
				param.put("invoiceNumber", invoiceNumber);
				
				List<InvoiceTrxD> trxDList= invoiceTrxDao.findInvoiceTrxDByParams(param);
				for (Iterator<InvoiceTrxD> k = trxDList.iterator(); k.hasNext(); ) {
					InvoiceTrxD trxD = k.next();
					trxD.setIsLock("1");
					this.update(trxD);
				}
			}
		}
		
		invoiceAllocDao.save(invoiceAllocH);
	}
	
	@Override
	public void deleteAlloc(String id) {
		InvoiceAllocH invoiceAllocH = (InvoiceAllocH) invoiceTrxDao.get(InvoiceAllocH.class, id);
		Hibernate.initialize(invoiceAllocH.getInvoiceAllocLs());
		invoiceTrxDao.removeAll(invoiceAllocH.getInvoiceAllocLs());
		invoiceTrxDao.remove(invoiceAllocH);
		
		List<InvoiceAllocL> list = (List<InvoiceAllocL>)invoiceAllocH.getInvoiceAllocLs();
		for (Iterator<InvoiceAllocL> i = list.iterator(); i.hasNext(); ) {
			InvoiceAllocL entity = i.next();
			for(int j=0;j<entity.getInvoiceAllotQty();j++){
				String invoiceNumber = String.format("%08d", (Integer.parseInt(entity.getStartInvoiceNumber())+j));
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("invoiceCode", entity.getInvoiceCode());
				param.put("invoiceNumber", invoiceNumber);
				
				List<InvoiceTrxD> trxDList= invoiceTrxDao.findInvoiceTrxDByParams(param);
				for (Iterator<InvoiceTrxD> k = trxDList.iterator(); k.hasNext(); ) {
					InvoiceTrxD trxD = k.next();
					trxD.setIsLock("0");
					this.update(trxD);
				}
			}
		}
	}
	
	@Override
	public List<InvoiceTrxD> queryValidAllocNumber(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}			
		return invoiceAllocDao.queryValidAllocNumber(params);
	}
	
	@Override
	public void auditInvoiceAllocById(String id) {
		InvoiceAllocL allocL = (InvoiceAllocL) invoiceTrxDao.get(InvoiceAllocL.class, id);
		InvoiceAllocH allocH = allocL.getInvoiceAllocH();
		if(!(allocH.getApprovalStatus().equals("distributed"))){
			Date date = getDatabaseServerDate();
			allocH.setApprovalStatus("distributed");
			allocH.setApprovalDate(date);
			this.update(allocH);
		}
		
		for(int j=0;j<allocL.getInvoiceAllotQty();j++){
			String invoiceNumber = String.format("%08d",(Integer.parseInt(allocL.getStartInvoiceNumber())+j));
			InvoiceAllocD detail = new InvoiceAllocD();
			detail.setInvoiceAllotLId(allocL.getId());
			detail.setInvoiceCode(allocL.getInvoiceCode());
			detail.setInvoiceNumber(invoiceNumber);
			detail.setInvoiceQty(1);
			detail.setLegalEntityId(allocL.getLegalEntityId());
			detail.setEquipmentId(allocL.getEquipmentId());
			this.save(detail);
			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("invoiceCode", allocL.getInvoiceCode());
			param.put("invoiceNumber", invoiceNumber);
			List<InvoiceTrxD> trxDList = invoiceTrxDao.findInvoiceTrxDByParams(param);
			for (Iterator<InvoiceTrxD> k = trxDList.iterator(); k.hasNext(); ) {
				InvoiceTrxD trxD = k.next();
				trxD.setEquipmentId(allocL.getEquipmentId());
				trxD.setIsLock("0");
				trxD.setStatus("2");
				this.update(trxD);
			}			
		}
	}
}

