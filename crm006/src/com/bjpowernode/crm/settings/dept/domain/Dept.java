package com.bjpowernode.crm.settings.dept.domain;

public class Dept {

	private String id;
	private String no;
	private String name;
	private String manager;
	private String phone;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Dept() {
		super();
	}

	public Dept(String id, String no, String name, String manager, String phone, String description) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.manager = manager;
		this.phone = phone;
		this.description = description;
	}

	public Dept(String no, String name, String manager, String phone, String description) {
		super();
		this.no = no;
		this.name = name;
		this.manager = manager;
		this.phone = phone;
		this.description = description;
	}

	public Dept(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", no=" + no + ", name=" + name + ", manager=" + manager + ", phone=" + phone
				+ ", description=" + description + "]";
	}

}
