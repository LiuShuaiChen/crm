package com.bjpowernode.crm.settings.dictionary.domain;

/**
 * 数据库 对应 tbl_dictionary_value表
 * 
 * @author LauShuaichen
 *
 */
public class DictionaryValue {

	private String id; // 字段id
	private String typeCode; // 字段typeCode
	private String value; // 字段value
	private String text; // 字段text
	private int orderNo; // 字段orderNo

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return typeCode;
	}

	public void setCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public DictionaryValue() {
		super();
	}

	public DictionaryValue(String id, String typeCode, String value, String text, int orderNo) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.value = value;
		this.text = text;
		this.orderNo = orderNo;
	}

	public DictionaryValue(String typeCode, String value, String text, int orderNo) {
		super();
		this.typeCode = typeCode;
		this.value = value;
		this.text = text;
		this.orderNo = orderNo;
	}

	public DictionaryValue(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "DictionartValue [id=" + id + ", typeCode=" + typeCode + ", value=" + value + ", text=" + text + ", orderNo="
				+ orderNo + "]";
	}


	
}
