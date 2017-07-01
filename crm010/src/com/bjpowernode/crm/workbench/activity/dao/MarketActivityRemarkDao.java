package com.bjpowernode.crm.workbench.activity.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;

public interface MarketActivityRemarkDao {
	
	
	/**
	 * 根据市场活动id  拿到对应的市场活动 备注 信息
	 * @param id
	 * @return
	 */
	List<MarketActivityRemark> queryMarketActivityRemarkByActivityId(String id);

}
