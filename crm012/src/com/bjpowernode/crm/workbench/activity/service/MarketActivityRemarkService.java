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


	/**
	 * 根据id删除备注
	 * @param id
	 * @return
	 */
	int deleteMarketActivityRemarkById(String id);


	/**
	 * 根据id 更新 市场备注内容
	 * @param id
	 * @return
	 */
	int updateMarketActivityRemarkById(MarketActivityRemark marketActivityRemark);
	
	

}
