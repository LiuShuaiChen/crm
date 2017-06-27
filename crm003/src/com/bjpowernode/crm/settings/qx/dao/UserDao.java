package com.bjpowernode.crm.settings.qx.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.domain.User;

public interface UserDao {
	
	void insert(User user);
	
	void delete(String id);
	
	void update(User user);
	
	User getById(String Id);
	
	List<User> getByAll();
	
	List<User> getByLimit(Map<String, Object>map);
	
	int getToal(Map<String, String>map);
	
	String getLockStatusById(String id);

	void updateLockStatus(String lockStatusRel, String id);
	


}
