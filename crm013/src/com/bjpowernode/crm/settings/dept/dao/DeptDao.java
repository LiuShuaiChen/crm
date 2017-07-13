package com.bjpowernode.crm.settings.dept.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.dept.domain.Dept;

public interface DeptDao {

	void insert(Dept dept);

	void delete(String id);

	void update(Dept dept);

	Dept getById(String id);

	List<Dept> getAll(Map<String, Integer> map);

	int getTotal();
	
	Dept getByName(String name);
	
	List<Dept> getByAll();

}
