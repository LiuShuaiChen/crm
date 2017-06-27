package com.bjpowernode.crm.settings.dictionary.domain;

/**
 * 数据库 对应 tbl_dictionary_value表
 * 
 * @author LauShuaichen
 *
 */
public class DictionaryValue {

	private String id; // 字段id
	private String code; // 字段code
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
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public DictionaryValue(String id, String code, String value, String text, int orderNo) {
		super();
		this.id = id;
		this.code = code;
		this.value = value;
		this.text = text;
		this.orderNo = orderNo;
	}

	public DictionaryValue(String code, String value, String text, int orderNo) {
		super();
		this.code = code;
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
		return "DictionartValue [id=" + id + ", code=" + code + ", value=" + value + ", text=" + text + ", orderNo="
				+ orderNo + "]";
	}


	
}
