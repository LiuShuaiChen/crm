package com.bjpowernode.crm.workbench.activity.service.impl;

import java.util.List;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityRemarkDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityRemarkService;

public class MarketActivityRemarkServiceImpl implements MarketActivityRemarkService {
	
	private MarketActivityRemarkDao marketActivityRemarkDao = SqlSessionutils.getSession().getMapper(MarketActivityRemarkDao.class);

	/**
	 * 根据市场活动id 查找对应的市场活动备注
	 */
	@Override
	public List<MarketActivityRemark> queryMarketActivityRemarkByActivityId(String id) {
		return marketActivityRemarkDao.queryMarketActivityRemarkByActivityId(id);
	}


	/**
	 *  在市场活动明细页中  添加备注
	 */
	@Override
	public int createMarketActivityRemaerk(MarketActivityRemark marketActivityRemark) {
		return marketActivityRemarkDao.createMarketActivityRemaerk(marketActivityRemark);
	}


	@Override
	public int deleteMarketActivityRemarkById(String id) {
		return marketActivityRemarkDao.deleteMarketActivityRemarkById(id);
	}


	@Override
	public int updateMarketActivityRemarkById(MarketActivityRemark marketActivityRemark) {
		return marketActivityRemarkDao.updateMarketActivityRemarkById(marketActivityRemark);
	}

}
