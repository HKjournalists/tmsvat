package com.deloitte.tms.base.masterdata.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryService;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoice;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoiceInParam;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalInvoiceDao;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalInvoiceService;
/**
 * Home object for domain model class TmsMdLegalInvoice.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdLegalInvoiceService.BEAN_ID)
public class TmsMdLegalInvoiceServiceImpl extends BaseService implements TmsMdLegalInvoiceService{
	@Resource
	TmsMdLegalInvoiceDao tmsMdLegalInvoiceDao;
	@Autowired
	DictionaryService dictionaryService;
	/*@Autowired
	TmsMdLegalInvoiceService tmsMdLegalInvoiceService;*/
	@Override
	public IDao getDao() {
		return tmsMdLegalInvoiceDao;
	}

	@Override
	public DaoPage findTmsMdLegalInvoiceByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}	
		if(AssertHelper.isOrNotEmpty_assert(params.get("legalEntityCode"))){
			TmsMdLegalEntity entity=this.findLegalEntity(params);
			params.put("legalEntityId", entity.getId());
		}
		DaoPage daoPage= tmsMdLegalInvoiceDao.findTmsMdLegalInvoiceByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdLegalInvoiceToInParam((List<TmsMdLegalInvoice>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<TmsMdLegalInvoiceInParam> findTmsMdLegalInvoiceByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsMdLegalInvoice> temp= tmsMdLegalInvoiceDao.findTmsMdLegalInvoiceByParams(params);
		return convertTmsMdLegalInvoiceToInParam(temp);
	}
	@Override
	public void saveTmsMdLegalInvoiceDataListsMap(Map dataListsMap) {
		Collection<TmsMdLegalInvoiceInParam> deleteList = BatchUtils.to(TmsMdLegalInvoiceInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsMdLegalInvoiceInParam> insertList =  BatchUtils.to(TmsMdLegalInvoiceInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsMdLegalInvoiceInParam> updateList =  BatchUtils.to(TmsMdLegalInvoiceInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsMdLegalInvoiceInParam inParam: updateList){
				TmsMdLegalInvoice entity=convertTmsMdLegalInvoiceInParamToEntity(inParam);
				tmsMdLegalInvoiceDao.update(entity);
			}		
//			tmsMdLegalInvoiceDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsMdLegalInvoiceInParam inParam: insertList){
				TmsMdLegalInvoice entity=convertTmsMdLegalInvoiceInParamToEntity(inParam);
				tmsMdLegalInvoiceDao.save(entity);
				inParam.setId(entity.getId());
			}
//			tmsMdLegalInvoiceDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsMdLegalInvoiceInParam inParam:deleteList){
				TmsMdLegalInvoice entity=new TmsMdLegalInvoice();
				entity.setId(inParam.getId());
				tmsMdLegalInvoiceDao.remove(entity);
			}
//			tmsMdLegalInvoiceDao.removeAll(deleteList);
		}
		
	}
	private List<TmsMdLegalInvoiceInParam> convertTmsMdLegalInvoiceToInParam(List<TmsMdLegalInvoice> models){
		List<TmsMdLegalInvoiceInParam> result=new ArrayList<TmsMdLegalInvoiceInParam>();
		for(TmsMdLegalInvoice initiation:models){
			TmsMdLegalInvoiceInParam inparam=convertTmsMdLegalInvoiceToInParam(initiation);
			inparam.setLegalEntityCode(initiation.getTmsMdLegalEntity().getLegalEntityCode());
			inparam.setLegalEntityName(initiation.getTmsMdLegalEntity().getLegalEntityName());
			List<DictionaryEntity>list=dictionaryService.loadDictionaryEntities("VAT_CR_INVOICE_TYPE");
			for (int i = 0; i < list.size(); i++) {
				if(initiation.getInvoiceCategory().equals(list.get(i).getCode())){
					inparam.setInvoiceTypeName(list.get(i).getName());
				}
			}
			result.add(inparam);
		}
		return result;
	}
	public TmsMdLegalInvoiceInParam convertTmsMdLegalInvoiceToInParam(TmsMdLegalInvoice model){
		TmsMdLegalInvoiceInParam inparam=new TmsMdLegalInvoiceInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	public TmsMdLegalInvoice convertTmsMdLegalInvoiceInParamToEntity(TmsMdLegalInvoiceInParam inParam){
		TmsMdLegalInvoice entity=new TmsMdLegalInvoice();
		ReflectUtils.copyProperties(inParam, entity);
		//entity.setInvoiceLimitAmountValue(inParam.getInvoiceLimitAmountValue());
		return entity;
	}

	
	/**   
	 * @return  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalInvoiceService#findLegalEntity()  
	 */
	
	@Override
	public TmsMdLegalEntity findLegalEntity(Map<String, Object> map) {
		TmsMdLegalEntity entity=tmsMdLegalInvoiceDao.findLegalEntity(map);
		return entity;
	}

	
	/**   
	 * @param ids  
	 * @see com.deloitte.tms.base.masterdata.service.TmsMdLegalInvoiceService#removeTmsLegalInvoices(java.lang.String[])  
	 */
	
	@Override
	public void removeTmsLegalInvoices(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			TmsMdLegalInvoice entity=(TmsMdLegalInvoice) tmsMdLegalInvoiceDao.get(TmsMdLegalInvoice.class, ids[i]);
			tmsMdLegalInvoiceDao.remove(entity);
		}
	}
}
