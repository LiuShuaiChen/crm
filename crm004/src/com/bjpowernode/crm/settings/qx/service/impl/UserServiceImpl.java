package com.bjpowernode.crm.settings.qx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.dao.UserDao;
import com.bjpowernode.crm.settings.qx.domain.User;
import com.bjpowernode.crm.settings.qx.service.UserService;
import com.bjpowernode.crm.utils.PageUtils;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;

/**
 * service userDao
 * 
 * @author LauShuaichen
 *
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao = SqlSessionutils.getSession().getMapper(UserDao.class);

	@Override
	public void save(User user) {
		userDao.insert(user);

	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			userDao.delete(id);
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);

	}

	@Override
	public User getById(String id) {
		return userDao.getById(id);
	}


	@Override
	public void changeStatus(String id) {
		String lockStatus = userDao.getLockStatusById(id);
		String lockStatusRel = "1";
		if("1".equals(lockStatus)){
			lockStatusRel = "0";
		}
		userDao.updateLockStatus(lockStatusRel,id);
		
	}

	@Override
	public Map<String, Object> getAll(String pageNoStr, String name, String deptId, String lockStatus, String startTime, String endTime) {

		//外层map  装两个子map  这个map 也是sql语句的参数
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		//条件查询的map
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		//分页查询的map
		Map<String, Integer> puMap = new HashMap<String, Integer>();
		
		//返回值map  装有 分页信息  数据列表
		Map<String, Object> reMap = new HashMap<String, Object>();
		
		//完成conditionMap 条件查询
		if (name != null && !"".equals(name.trim())) {
			conditionMap.put("name", "%"+ name +"%");
		}else {
			conditionMap.put("name", name);
		}
		
		conditionMap.put("lockStatus", lockStatus);
		
		conditionMap.put("startTime", startTime);
		
		conditionMap.put("endTime", endTime);
		
		//完成分页查询 map
		//设置当前页
		int pageNo = 1;
		if (pageNoStr != null && !"".equals(pageNoStr.trim())) {
			pageNo = Integer.valueOf(pageNoStr);
		}
		
		//设置每页显示5条记录
		int pageCount = 5;
		
		//查询一共有多少条记录
		int total = userDao.getTotal(conditionMap);
		
		//推算一共有多少页
		int pageSize = total / pageCount;
		if (total % pageCount > 0) {
			pageSize++;
		}

		//分页信息对象
		PageUtils pu = new PageUtils(pageNo, pageCount, pageSize, total);

		int skipCount = pageCount * (pageNo - 1);
		

		puMap.put("skipCount", skipCount);
		puMap.put("pageCount", pageCount);
		
		dataMap.put("puMap", puMap);
		dataMap.put("conditionMap", conditionMap);
		
		List<User> uList = userDao.getByAll(dataMap);
		
		reMap.put("pu", pu);
		reMap.put("uList", uList);
		
		return reMap;
	}


	public static void main(String[] args) {
		UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
		User user = userService.getById("354b420db09e42ac95d0cf64d3267fb8");
		System.out.println(user);

	}

}
