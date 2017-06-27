package com.bjpowernode.crm.settings.dictionary.dao;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;

/**
 * 数据字典值 持久化操作接口
 * @author LauShuaichen
 *
 */
public interface DictionaryValueDao {

	void insert(DictionaryValue dictionaryValue);

	void delete(String id);

	void update(DictionaryValue dictionaryValue);

	DictionaryValue getById(String id);

	List<DictionaryValue> getByAll();
	
	/**
	 * 根据类型查询数据字典值列表
	 * @param typeCode
	 * @return
	 */
	List<DictionaryValue> queryDicValueByType(String typeCode);

}
