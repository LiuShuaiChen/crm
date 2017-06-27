package com.bjpowernode.crm.settings.dictionary.service.impl;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.dao.DictionaryTypeDao;
import com.bjpowernode.crm.settings.dictionary.domain.DictionaryType;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryTypeService;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;

/**
 * 实现service接口方法
 * 
 * @author LauShuaichen
 *
 */
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

	private DictionaryTypeDao dictionaryDao = SqlSessionutils.getSession().getMapper(DictionaryTypeDao.class);

	@Override
	public void insert(DictionaryType dictionaryType) {
		dictionaryDao.insert(dictionaryType);

	}

	@Override
	public void delete(String[] codes) {
		for (String code : codes) {
			dictionaryDao.delete(code);
		}
	}

	@Override
	public void update(DictionaryType dictionaryType) {
		dictionaryDao.update(dictionaryType);

	}

	@Override
	public DictionaryType getByCode(String code) {
		return dictionaryDao.getByCode(code);
	}

	@Override
	public List<DictionaryType> getByAll() {
		return dictionaryDao.getByAll();
	}

	@Override
	public void getCount(String code) {
		dictionaryDao.getByCode(code);
	}

//	public static void main(String[] args) {
//		DictionaryTypeDao dictionaryDao = SqlSessionutils.getSession().getMapper(DictionaryTypeDao.class);
//		System.out.println(dictionaryDao.getByAll());
//		DictionaryType dictionaryType = new DictionaryType("哈哈", "呵呵", "哦哦");
//		dictionaryDao.insert(dictionaryType);
//		SqlSessionutils.getSession().commit();
//	}
	
	
//	public static void main(String[] args) {
//		DictionaryTypeServiceImpl dictionaryTypeServiceImpl = new DictionaryTypeServiceImpl();
//		DictionaryType dictionaryType = dictionaryTypeServiceImpl.getByCode("day");
//		System.out.println(dictionaryType);
//	}
	
//	public static void main(String[] args) {
//		DictionaryTypeService DictionaryTypeService = (DictionaryTypeServiceImpl) ServiceFactory.getService(new DictionaryTypeServiceImpl());
//		DictionaryTypeService.delete("name");
//	}
	
	public static void main(String[] args) {
		DictionaryTypeService DictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		List<DictionaryType> dicList = DictionaryTypeService.getByAll();
		for (DictionaryType dictionaryType : dicList) {
			System.out.println(dictionaryType);
		}
		
	}

}
