package com.bjpowernode.crm.workbench.activity.service.impl;

import java.util.List;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityRemarkDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;

public class MarketActivityRemarkServiceImpl implements MarketActivityRemarkService {
	
	private MarketActivityRemarkDao marketActivityRemarkDao = SqlSessionutils.getSession().getMapper(MarketActivityRemarkDao.class);

	@Override
	public List<MarketActivityRemark> queryMarketActivityRemarkByActivityId(String id) {
		return marketActivityRemarkDao.queryMarketActivityRemarkByActivityId(id);
	}

}
