package com.bjpowernode.crm.settings.qx.service;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.domain.User;

public interface UserService {

	
	void save(User user);
	
	void delete(String[] ids);
	
	void update(User user);
	
	User getById(String id);
	
	List<User> getByAll();
	
	Map<String, Object> getLimit (String pageStr, String name, String deptName, String lockStatus, String startTime, String endTime);
	
	void changeStatus(String id);
}
