package com.bjpowernode.crm.settings.dictionary.domain;

/**
 * 对应 tbl_dictionary_type 表
 * @author LauShuaichen
 *
 */
public class DictionaryType {

	private String code;	//字段code
	private String name;	//字段name
	private String description;	//字段description

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DictionaryType [code=" + code + ", name=" + name + ", description=" + description + "]";
	}

	public DictionaryType() {
		super();
	}

	public DictionaryType(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	
	

}
