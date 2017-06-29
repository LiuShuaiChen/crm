package com.bjpowernode.crm.workbench.activity.service.impl;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;

/**
 * 
 * @author LauShuaichen
 *
 */
public class MarketActivityServiceImpl implements MarketActivityService {
	
	private MarketActivityDao marketActivityDao = SqlSessionutils.getSession().getMapper(MarketActivityDao.class);
	

	/**
	 * 市场活动创建
	 */
	@Override
	public int insertCreateMarketActivity(MarketActivity marketActivity) {
		return marketActivityDao.insertCreateMarketActivity(marketActivity);
	}

}
