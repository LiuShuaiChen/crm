package com.bjpowernode.crm.settings.qx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.qx.dao.UserDao;
import com.bjpowernode.crm.settings.qx.domain.User;
import com.bjpowernode.crm.settings.qx.service.UserService;
import com.bjpowernode.crm.utils.PageUtils;
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
	public List<User> getByAll() {
		return userDao.getByAll();
	}

	@Override
	public Map<String, Object> getLimit(String pageNoStr, String name, String deptName, String lockStatus,
			String startTime, String endTime) {
		
		//外层map  里面需要 装两个子map  这个map也是sql语句中的参数
		Map<String, Object>dataMap = new HashMap<String, Object>();
		
		//条件查询的map
		Map<String, String> map1 = new HashMap<String, String>();
		
		//分页信息的map
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		
		//返回值map 里面装有 1分页信息  2数据列表
		Map<String, Object>reMap = new HashMap<String, Object>();
		
		//开始万恒dataMap
		//首先完成map1
		//注意:map中的key必须有
		if (name != null && !"".equals(name.trim())) {
			map1.put("name", "%" + name + "%" );	//解决字符串变成%null%  % %的问题
		}else {
			map1.put("name", name);
		}
		
		if (deptName != null && !"".equals(deptName.trim())) {
			map1.put("deptName", "%" + deptName + "%");
		}else {
			map1.put("deptName", deptName);
		}
		
		map1.put("lockStatus", lockStatus);
		map1.put("startTime", startTime);
		map1.put("endTime", endTime);
		
		//完成map2
		int pageNo = 1;
		if (pageNoStr != null && !"".equals(pageNoStr.trim())) {
			pageNo = Integer.valueOf(pageNoStr);
		}
		
		int pageCount = 5;
		
		int total = userDao.getToal(map1);
		
		int pageSize = total / pageCount;
		if (total % pageCount > 0) {
			pageSize++;
		}
		
		//返回第一个元素
		PageUtils pu = new PageUtils();
		pu.setPageNo(pageNo);
		pu.setPageCount(pageCount);
		pu.setPageSize(pageSize);
		pu.setTotal(total);
		
		int skipCount = (pageNo - 1) * pageCount;
		map2.put("skipCount", skipCount);
		map2.put("pageCount", pageCount);
		
		//将map1 和 map2 放入到dataMap中
		dataMap.put("map1", map1);
		dataMap.put("map2", map2);
		
		//获取第二个元素
		List<User> uList = userDao.getByLimit(dataMap);
		
		
		//两个元素都有了  将两个元素放到returnMap里
		reMap.put("pu", pu);
		reMap.put("uList", uList);
		
		return reMap;
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

}
