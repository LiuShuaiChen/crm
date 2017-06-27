package com.bjpowernode.crm.settings.qx.user.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.user.domain.User;


public interface UserDao {
	
	void insert(User user);
	
	void delete(String id);
	
	void update(User user);
	
	User getById(String Id);
	
	List<User> getByAll(Map<String, Object>map);
	
	int getTotal(Map<String, String>map);
	
	// 查询 锁定状态
	String getLockStatusById(String id);

	// 修改 锁定状态
	void updateLockStatus(String lockStatusRel, String id);
	
	


}
