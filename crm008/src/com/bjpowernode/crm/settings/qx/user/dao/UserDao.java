package com.bjpowernode.crm.settings.qx.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 获取账户密码 组合
	 * @param act
	 * @param pwd
	 * @return
	 */
	//User getActAndPwd(String act, String pwd);
	User getActAndPwd(User user);
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> queryAllUsers();
	


}
