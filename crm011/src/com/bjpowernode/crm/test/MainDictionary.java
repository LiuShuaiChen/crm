package com.bjpowernode.crm.test;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryType;
import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryTypeService;
import com.bjpowernode.crm.settings.dictionary.service.DictionaryValueService;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryTypeServiceImpl;
import com.bjpowernode.crm.settings.dictionary.service.impl.DictionaryValueServiceImpl;
import com.bjpowernode.crm.utils.ServiceFactory;

/**
 * 
 * @author LauShuaichen
 *
 */
public class MainDictionary {
	
	public static void list(){
		DictionaryTypeService dictionaryTypeService = (DictionaryTypeService) ServiceFactory.getService(new DictionaryTypeServiceImpl());
		List<DictionaryType> dicList = dictionaryTypeService.getByAll();
		for (DictionaryType d : dicList) {
			System.out.println(d);
		}
	}
	
	public static void valueList(){
		DictionaryValueService dictionaryValueService = (DictionaryValueService) ServiceFactory.getService(new DictionaryValueServiceImpl());
		List<DictionaryValue> dictionaryValues = dictionaryValueService.getByAll();
		for (DictionaryValue d : dictionaryValues) {
			System.out.println(d);
		}
	}
	

	
	
	

	public static void main(String[] args) {
//		list();
//		delete();
//		valueList();
		
	}

}
