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


	/**
	 * 新增市场活动明细页中的 备注
	 * @param marketActivityRemark
	 * @return
	 */
	int createMarketActivityRemaerk(MarketActivityRemark marketActivityRemark);
	
	

}
