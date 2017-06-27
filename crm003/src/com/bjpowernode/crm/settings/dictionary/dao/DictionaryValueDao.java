package com.bjpowernode.crm.settings.dictionary.dao;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;

/**
 * 
 * @author LauShuaichen
 *
 */
public interface DictionaryValueDao {

	void insert(DictionaryValue dictionaryValue);

	void delete(String id);

	void update(DictionaryValue dictionaryValue);

	DictionaryValue getById(String id);

	List<DictionaryValue> getByAll();

}
