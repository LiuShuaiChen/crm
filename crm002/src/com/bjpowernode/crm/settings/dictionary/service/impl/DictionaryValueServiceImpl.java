package com.bjpowernode.crm.settings.dictionary.service.impl;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.dao.DictionaryValueDao;
import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;

/**
 * 
 * @author LauShuaichen
 *
 */
public class DictionaryValueServiceImpl implements DictionaryValueService {

	private DictionaryValueDao dictionaryValueDao = SqlSessionutils.getSession().getMapper(DictionaryValueDao.class);

	@Override
	public void insert(DictionaryValue dictionaryValue) {
		dictionaryValueDao.insert(dictionaryValue);
	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			dictionaryValueDao.delete(id);
		}
	}

	@Override
	public void update(DictionaryValue dictionaryValue) {
		dictionaryValueDao.update(dictionaryValue);
	}

	@Override
	public DictionaryValue getById(String id) {
		return dictionaryValueDao.getById(id);
	}

	@Override
	public List<DictionaryValue> getByAll() {
		return dictionaryValueDao.getByAll();
	}


	public static void main(String[] args) {
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
//		List<DictionaryValue> dList = dictionaryValueService.getByAll();
		
		DictionaryValue dictionaryValue = dictionaryValueService.getById("33333333333333333333333333333332");
		
		
		System.out.println(dictionaryValue.toString());
		
//		for (DictionaryValue dic : dList) {
//			System.out.println(dic.toString());
//		}
	}


}
