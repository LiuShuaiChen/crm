package com.bjpowernode.crm.settings.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.settings.dept.dao.DeptDao;
import com.bjpowernode.crm.settings.dept.domain.Dept;
import com.bjpowernode.crm.settings.dept.service.DeptService;
import com.bjpowernode.crm.utils.PageUtils;
import com.bjpowernode.crm.utils.SqlSessionutils;

public class DeptServiceImpl implements DeptService {
	
	private DeptDao deptDao = SqlSessionutils.getSession().getMapper(DeptDao.class);

	@Override
	public void insert(Dept dept) {
		deptDao.insert(dept);

	}

	@Override
	public void delete(String[] ids) {
		for (String id : ids) {
			deptDao.delete(id);
		}
	}

	@Override
	public void update(Dept dept) {
		deptDao.update(dept);

	}

	@Override
	public Dept getById(String id) {
		return deptDao.getById(id);
	}

	@Override
	public Map<String, Object> getAll(String pageNoStr) {
		
		//设置当前页
		int pageNo = 1;
		
		
		if (pageNoStr != null) {
			pageNo = Integer.valueOf(pageNoStr);
		}

		//每页5条记录
		int pageCount = 5;
		//查询一共有多少条记录
		int total = deptDao.getTotal();
		
		//推算一共有多少页
		int pageSize = total / pageCount;
		if (total % pageCount > 0) {
			pageSize++;
		}
		
		// 分页信息 对象
		PageUtils pu = new PageUtils();
		pu.setPageCount(pageCount);
		pu.setPageNo(pageNo);
		pu.setPageSize(pageSize);
		pu.setTotal(total);
		
		int skipCount = pageCount * (pageNo - 1);
		
		Map<String, Integer>dataMap = new HashMap<String, Integer>();
		dataMap.put("skipCount", skipCount);
		dataMap.put("pageCount", pageCount);
		
		List<Dept> dList = deptDao.getAll(dataMap);
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		reMap.put("pu", pu);
		reMap.put("dList", dList);
		
		return reMap;
	}

	@Override
	public int getTotal() {
		return deptDao.getTotal();
	}

}
