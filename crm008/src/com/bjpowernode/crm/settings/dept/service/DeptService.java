package com.bjpowernode.crm.settings.dept.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.dept.domain.Dept;

public interface DeptService {

	void insert(Dept dept);

	void delete(String[] ids);

	void update(Dept dept);

	Dept getById(String id);

	Map<String, Object> getAll(String pageNoStr);

	int getTotal();
	
	
	Dept getByName(String name);
	
	List<Dept> getByAll();
}
