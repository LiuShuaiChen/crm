package com.bjpowernode.crm.settings.dictionary.service;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryType;

public interface DictionaryTypeService {

	void insert(DictionaryType dictionaryType);

	void delete(String[] codes);

	void update(DictionaryType dictionaryType);

	DictionaryType getByCode(String code);
	
	List<DictionaryType> getByAll();
	
	void getCount(String code);

}
