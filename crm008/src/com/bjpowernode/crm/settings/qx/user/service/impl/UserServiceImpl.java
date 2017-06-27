package com.bjpowernode.crm.settings.qx.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.security.exceptions.ApplicationException;
import com.bjpowernode.crm.settings.qx.user.dao.UserDao;
import com.bjpowernode.crm.settings.qx.user.domain.User;
import com.bjpowernode.crm.settings.qx.user.service.UserService;
import com.bjpowernode.crm.utils.DateUtils;
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
	public void changeStatus(String id) {
		String lockStatus = userDao.getLockStatusById(id);
		String lockStatusRel = "1";
		if ("1".equals(lockStatus)) {
			lockStatusRel = "0";
		}
		userDao.updateLockStatus(lockStatusRel, id);

	}

	@Override
	public Map<String, Object> getAll(String pageNoStr, String name, String deptId, String lockStatus, String startTime,
			String endTime) {

		// 外层map 装两个子map 这个map 也是sql语句的参数
		Map<String, Object> dataMap = new HashMap<String, Object>();

		// 条件查询的map
		Map<String, String> conditionMap = new HashMap<String, String>();

		// 分页查询的map
		Map<String, Integer> puMap = new HashMap<String, Integer>();

		// 返回值map 装有 分页信息 数据列表
		Map<String, Object> reMap = new HashMap<String, Object>();

		// 完成conditionMap 条件查询
		if (name != null && !"".equals(name.trim())) {
			conditionMap.put("name", "%" + name + "%");
		} else {
			conditionMap.put("name", name);
		}

		conditionMap.put("lockStatus", lockStatus);

		conditionMap.put("startTime", startTime);

		conditionMap.put("endTime", endTime);

		// 完成分页查询 map
		// 设置当前页
		int pageNo = 1;
		if (pageNoStr != null && !"".equals(pageNoStr.trim())) {
			pageNo = Integer.valueOf(pageNoStr);
		}

		// 设置每页显示5条记录
		int pageCount = 5;

		// 查询一共有多少条记录
		int total = userDao.getTotal(conditionMap);

		// 推算一共有多少页
		int pageSize = total / pageCount;
		if (total % pageCount > 0) {
			pageSize++;
		}

		// 分页信息对象
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




	@Override
	public User login(String act, String pwd, String ip) {
		User user = userDao.getActAndPwd(act, pwd);

		// 判断用户名 密码是否正确
		if (user == null) {
			throw new ApplicationException("用户名或者密码错误,登录失败");
		}

		// 判断ip是否受限
		String ips = user.getAllowIps();
		if (ips != null && !"".equals(ips.trim())) {
			if (!ips.contains(ip)) {
				throw new ApplicationException("ip地址受限,请联系管理员");
			}
		}

		// 判断失效时间
		String expireTime = user.getExpireTime();
		if (expireTime != null && !"".equals(expireTime.trim())) {
			// 获取当前时间
			String currentDateTime = DateUtils.getDate();
			if (expireTime.compareTo(currentDateTime) < 0) {
				throw new ApplicationException("用户失效,请联系管理员");
			}
		}

		// 判断用户是否锁定
		String lockStatus = user.getLockStatus();
		if ("1".equals(lockStatus)) {
			throw new ApplicationException("用户已经被锁定,请联系管理员");
		}
		return user;
	}

	/**
	 * 查询所有用户
	 */
	@Override
	public List<User> queryAllUsers() {
		return userDao.queryAllUsers();
	}

}
