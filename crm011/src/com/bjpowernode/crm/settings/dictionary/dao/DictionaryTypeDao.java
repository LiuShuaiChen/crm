package com.bjpowernode.crm.settings.dictionary.dao;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryType;

/**
 * 
 * @author LauShuaichen
 *
 */
public interface DictionaryTypeDao {

	void insert(DictionaryType dictionaryType);

	void delete(String code);

	void update(DictionaryType dictionaryType);

	DictionaryType getByCode(String code);

	List<DictionaryType> getByAll();

	int getCount(String code);

}
