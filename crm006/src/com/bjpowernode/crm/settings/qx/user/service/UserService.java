package com.bjpowernode.crm.settings.qx.user.service;

import java.util.Map;

import com.bjpowernode.crm.settings.qx.user.domain.User;

public interface UserService {

	
	void save(User user);
	
	void delete(String[] ids);
	
	void update(User user);
	
	User getById(String id);
	
	
	Map<String, Object> getAll(String pageNoStr, String name, String deptId, String lockStatus, String startTime, String endTime);
	
	void changeStatus(String id);
	

}


