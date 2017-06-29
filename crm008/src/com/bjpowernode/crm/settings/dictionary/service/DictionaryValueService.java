package com.bjpowernode.crm.settings.dictionary.service;

import java.util.List;

import com.bjpowernode.crm.settings.dictionary.domain.DictionaryValue;

/**
 * 数据字典业务操作接口
 * @author LauShuaichen
 *
 */
public interface DictionaryValueService {

	void insert(DictionaryValue dictionaryValue);

	void delete(String[] ids);

	void update(DictionaryValue dictionaryValue);

	DictionaryValue getById(String id);

	/**
	 * 根据类型查询数据字典值列表
	 * @param typeCode
	 * @return
	 */
	List<DictionaryValue> queryDicValueByType(String typeCode);
	
	/**
	 * 根据 *等级* 查询数据字典值列表
	 * @param grade
	 * @return
	 */
	List<DictionaryValue> queryDicValueBygrade(String grade);

	
	
	List<DictionaryValue> getByAll();
	
	

}
