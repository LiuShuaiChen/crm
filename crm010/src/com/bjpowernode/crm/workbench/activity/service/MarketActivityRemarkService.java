package com.bjpowernode.crm.workbench.activity.service;

import java.util.List;

import com.bjpowernode.crm.workbench.activity.domain.MarketActivityRemark;

public interface MarketActivityRemarkService {

	/**
	 * 根据市场活动id 拿到 市场活动备注
	 * @param id
	 * @return
	 */
	List<MarketActivityRemark> queryMarketActivityRemarkByActivityId(String id);
	
	

}
