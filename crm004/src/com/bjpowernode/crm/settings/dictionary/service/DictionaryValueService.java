package com.bjpowernode.crm.settings.dictionary.service;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;

public interface DictionaryValueService {

	void insert(DictionaryValue dictionaryValue);

	void delete(String[] ids);

	void update(DictionaryValue dictionaryValue);

	DictionaryValue getById(String id);

	List<DictionaryValue> getByAll();

}
