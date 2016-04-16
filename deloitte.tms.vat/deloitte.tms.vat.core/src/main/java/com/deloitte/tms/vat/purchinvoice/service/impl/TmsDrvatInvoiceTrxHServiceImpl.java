package com.deloitte.tms.vat.purchinvoice.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.enums.DataStatus;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.core.common.ExcelProcess;
import com.deloitte.tms.vat.purchinvoice.model.TmsDrvatInvoiceTrxH;
import com.deloitte.tms.vat.purchinvoice.model.TmsDrvatInvoiceTrxHInParam;
import com.deloitte.tms.vat.purchinvoice.model.TmsDrvatInvoiceTrxL;
import com.deloitte.tms.vat.purchinvoice.model.TmsDrvatInvoiceTrxLInParam;
import com.deloitte.tms.vat.purchinvoice.dao.TmsDrvatInvoiceTrxHDao;
import com.deloitte.tms.vat.purchinvoice.service.TmsDrvatInvoiceTrxHService;
/**
 * Home object for domain model class TmsDrvatInvoiceTrxH.
 * @see com.deloitte.tms.vat.purchinvoice.model
 * @author Hibernate Tools
 */
@Component(TmsDrvatInvoiceTrxHService.BEAN_ID)
public class TmsDrvatInvoiceTrxHServiceImpl extends BaseService implements TmsDrvatInvoiceTrxHService{
	
	public static Integer tran4Records=50;
	
	@Resource
	TmsDrvatInvoiceTrxHDao tmsDrvatInvoiceTrxHDao;

	@Override
	public IDao getDao() {
		return tmsDrvatInvoiceTrxHDao;
	}
	
	@Override
	public void writeBackAuthen(TmsDrvatInvoiceTrxLInParam record){
		
		tmsDrvatInvoiceTrxHDao.writeBackAuthen( record);
	}

	@Override
	public DaoPage findTmsDrvatInvoiceTrxHByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxHByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsDrvatInvoiceTrxHToInParam((List<TmsDrvatInvoiceTrxH>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<TmsDrvatInvoiceTrxHInParam> findTmsDrvatInvoiceTrxHByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsDrvatInvoiceTrxH> temp= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxHByParams(params);
		return convertTmsDrvatInvoiceTrxHToInParam(temp);
	}
	@Override
	public void saveTmsDrvatInvoiceTrxHDataListsMap(Map dataListsMap) {
		Collection<TmsDrvatInvoiceTrxHInParam> deleteList = BatchUtils.to(TmsDrvatInvoiceTrxHInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxHInParam> insertList =  BatchUtils.to(TmsDrvatInvoiceTrxHInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxHInParam> updateList =  BatchUtils.to(TmsDrvatInvoiceTrxHInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsDrvatInvoiceTrxHInParam inParam: updateList){
				TmsDrvatInvoiceTrxH entity=convertTmsDrvatInvoiceTrxHInParamToEntity(inParam);
				tmsDrvatInvoiceTrxHDao.update(entity);
			}		
//			tmsDrvatInvoiceTrxHDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsDrvatInvoiceTrxHInParam inParam: insertList){
				TmsDrvatInvoiceTrxH entity=convertTmsDrvatInvoiceTrxHInParamToEntity(inParam);
				tmsDrvatInvoiceTrxHDao.save(entity);
				inParam.setId(entity.getId());
			}
//			tmsDrvatInvoiceTrxHDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsDrvatInvoiceTrxHInParam inParam:deleteList){
				TmsDrvatInvoiceTrxH entity=new TmsDrvatInvoiceTrxH();
				entity.setId(inParam.getId());
				tmsDrvatInvoiceTrxHDao.remove(entity);
			}
//			tmsDrvatInvoiceTrxHDao.removeAll(deleteList);
		}
		
	}
	private List<TmsDrvatInvoiceTrxHInParam> convertTmsDrvatInvoiceTrxHToInParam(List<TmsDrvatInvoiceTrxH> models){
		List<TmsDrvatInvoiceTrxHInParam> result=new ArrayList<TmsDrvatInvoiceTrxHInParam>();
		for(TmsDrvatInvoiceTrxH initiation:models){
			TmsDrvatInvoiceTrxHInParam inparam=convertTmsDrvatInvoiceTrxHToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public TmsDrvatInvoiceTrxHInParam convertTmsDrvatInvoiceTrxHToInParam(TmsDrvatInvoiceTrxH model){
		TmsDrvatInvoiceTrxHInParam inparam=new TmsDrvatInvoiceTrxHInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public TmsDrvatInvoiceTrxH convertTmsDrvatInvoiceTrxHInParamToEntity(TmsDrvatInvoiceTrxHInParam inParam){
		TmsDrvatInvoiceTrxH entity=new TmsDrvatInvoiceTrxH();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
	@Override
	public DaoPage findTmsDrvatInvoiceTrxLByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsDrvatInvoiceTrxLToInParam((List<TmsDrvatInvoiceTrxL>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<TmsDrvatInvoiceTrxLInParam> findTmsDrvatInvoiceTrxLByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsDrvatInvoiceTrxL> temp= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByParams(params);
		return convertTmsDrvatInvoiceTrxLToInParam(temp);
	}
	
	
	@Override
	public DaoPage findTmsDrvatInvoiceTrxLByParams2(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByParams2(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsDrvatInvoiceTrxLToInParam((List<TmsDrvatInvoiceTrxL>) daoPage.getResult()));
		return daoPage;
	}
	
	@Override
	public List<TmsDrvatInvoiceTrxLInParam> findTmsDrvatInvoiceTrxLByParams2(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsDrvatInvoiceTrxL> temp= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByParams2(params);
		return convertTmsDrvatInvoiceTrxLToInParam(temp);
	}
	
	@Override
	public List<TmsDrvatInvoiceTrxLInParam> findTmsDrvatInvoiceTrxLByDate(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsDrvatInvoiceTrxL> temp= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByDate(params);
		return convertTmsDrvatInvoiceTrxLToInParam(temp);
	}
	
	@Override
	public List<TmsDrvatInvoiceTrxLInParam> findTrxL4Exist(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsDrvatInvoiceTrxL> temp= tmsDrvatInvoiceTrxHDao.findTmsDrvatInvoiceTrxLByParams2(params);
		return convertTmsDrvatInvoiceTrxLToInParam(temp);
	}
	
		
	
	@Override
	public void saveTmsDrvatInvoiceTrxLDataListsMap(Map dataListsMap) {
		Collection<TmsDrvatInvoiceTrxLInParam> deleteList = BatchUtils.to(TmsDrvatInvoiceTrxLInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxLInParam> insertList =  BatchUtils.to(TmsDrvatInvoiceTrxLInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxLInParam> updateList =  BatchUtils.to(TmsDrvatInvoiceTrxLInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsDrvatInvoiceTrxLInParam inParam: updateList){
				TmsDrvatInvoiceTrxL entity=convertTmsDrvatInvoiceTrxLInParamToEntity(inParam);
				tmsDrvatInvoiceTrxHDao.update(entity);
			}		
//			tmsDrvatInvoiceTrxHDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsDrvatInvoiceTrxLInParam inParam: insertList){
				TmsDrvatInvoiceTrxL entity=convertTmsDrvatInvoiceTrxLInParamToEntity(inParam);
				tmsDrvatInvoiceTrxHDao.save(entity);
				inParam.setId(entity.getId());
			}
//			tmsDrvatInvoiceTrxHDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsDrvatInvoiceTrxLInParam inParam:deleteList){
				TmsDrvatInvoiceTrxL entity=new TmsDrvatInvoiceTrxL();
				entity.setId(inParam.getId());
				tmsDrvatInvoiceTrxHDao.remove(entity);
			}
//			tmsDrvatInvoiceTrxHDao.removeAll(deleteList);
		}
	}
	private List<TmsDrvatInvoiceTrxLInParam> convertTmsDrvatInvoiceTrxLToInParam(List<TmsDrvatInvoiceTrxL> models){
		List<TmsDrvatInvoiceTrxLInParam> result=new ArrayList<TmsDrvatInvoiceTrxLInParam>();
		for(TmsDrvatInvoiceTrxL initiation:models){
			TmsDrvatInvoiceTrxLInParam inparam=convertTmsDrvatInvoiceTrxLToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public TmsDrvatInvoiceTrxLInParam convertTmsDrvatInvoiceTrxLToInParam(TmsDrvatInvoiceTrxL model){
		TmsDrvatInvoiceTrxLInParam inparam=new TmsDrvatInvoiceTrxLInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public TmsDrvatInvoiceTrxL convertTmsDrvatInvoiceTrxLInParamToEntity(TmsDrvatInvoiceTrxLInParam inParam){
		TmsDrvatInvoiceTrxL entity=new TmsDrvatInvoiceTrxL();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
	
	
	
	@Override
	public void saveTmsInVoiceLMap(Map dataListsMap) {
		Collection<TmsDrvatInvoiceTrxL> deleteList = BatchUtils.to(TmsDrvatInvoiceTrxL.class).getDeleteEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxL> insertList =  BatchUtils.to(TmsDrvatInvoiceTrxL.class).getInsertEntities(dataListsMap);
		Collection<TmsDrvatInvoiceTrxL> updateList =  BatchUtils.to(TmsDrvatInvoiceTrxL.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsDrvatInvoiceTrxL inParam: updateList){
				
				tmsDrvatInvoiceTrxHDao.update(inParam);
			}		
//			tmsDrvatInvoiceTrxHDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsDrvatInvoiceTrxL inParam: insertList){
				
				tmsDrvatInvoiceTrxHDao.save(inParam);
				
			}
//			tmsDrvatInvoiceTrxHDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsDrvatInvoiceTrxL inParam:deleteList){
				
			
				tmsDrvatInvoiceTrxHDao.remove(inParam);
			}
//			tmsDrvatInvoiceTrxHDao.removeAll(deleteList);
		}
	}
	
	@Override
	public boolean importExcel(String importExcelName){
		
		try{
		
		ExcelProcess excelProcess = new ExcelProcess();
		excelProcess.importExcelInit(importExcelName);
		
		//the rowNum exclude header
		Integer rowNum = excelProcess.getRowNum(excelProcess.sheetIndexDefault);
		
		if(rowNum > tran4Records){
			
		//ArrayList<String[]> eachTran = new ArrayList<String[]>();
		
		
		
		Integer times =	rowNum/tran4Records;
		Integer left = rowNum%tran4Records;
		
		int j=0;
		
		for( ; j<times; j++){
			
			ArrayList<TmsDrvatInvoiceTrxL> eachTranModle = new ArrayList<TmsDrvatInvoiceTrxL>();
		
			for(int i = 0; i<tran4Records; i++){
				
				int point = j*tran4Records +i+ 1;
				
			  String[] rowData = excelProcess.getRowData(excelProcess.sheetIndexDefault, point);
			  
			  eachTranModle.add(new TmsDrvatInvoiceTrxL(rowData));
			 //eachTran.add(rowData);
			}
			
			HashMap map = new HashMap();
			
			map.put(DataStatus.INSERT, eachTranModle);
			
			this.saveTmsInVoiceLMap(map);
			//this.saveTmsDrvatInvoiceTrxLDataListsMap(map);
			
			}
		
		
		ArrayList<TmsDrvatInvoiceTrxL> eachTranModleLeft = new ArrayList<TmsDrvatInvoiceTrxL>();
		for(int le=0; le<left; le++){
			
				
				int point = j*tran4Records + le + 1;
				
			  String[] rowData = excelProcess.getRowData(excelProcess.sheetIndexDefault, point);
			  
			  eachTranModleLeft.add(new TmsDrvatInvoiceTrxL(rowData));
			
		}
			HashMap map = new HashMap();
			
			map.put(DataStatus.INSERT, eachTranModleLeft);
			
			this.saveTmsInVoiceLMap(map);
			
		
		
		
		}else{
			
			
			
					ArrayList<TmsDrvatInvoiceTrxL> eachTranModleLeft = new ArrayList<TmsDrvatInvoiceTrxL>();
					for(int le=0; le<rowNum; le++){
						
							
							
						  String[] rowData = excelProcess.getRowData(excelProcess.sheetIndexDefault, le+1);
						  
						  eachTranModleLeft.add(new TmsDrvatInvoiceTrxL(rowData));
						
					}
						HashMap map = new HashMap();
						
						map.put(DataStatus.INSERT, eachTranModleLeft);
						
						this.saveTmsInVoiceLMap(map);
					
		}
		
		return true;
		
	}catch(Exception ex){
		ex.printStackTrace();
		return false;
	}
		
		
	}
	
}

