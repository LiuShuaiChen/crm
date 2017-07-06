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


	/**
	 * 添加市场活动备注
	 * @param marketActivityRemark
	 * @return
	 */
	int createMarketActivityRemaerk(MarketActivityRemark marketActivityRemark);


	/**
	 * 删除市场活动备注
	 * @param id
	 * @return
	 */
	int deleteMarketActivityRemarkById(String id);


	/**
	 * 更新市场活动备注内容
	 * @param id
	 * @return
	 */
	int updateMarketActivityRemarkById(MarketActivityRemark marketActivityRemark);


	/**
	 * 删除市场活动 级联删除市场活动备注
	 * @param id
	 */
	void deleteMarketActivityRemarkByActivityId(String id);


	/**
	 * 删除市场活动 级联删除市场活动备注
	 * @param ids
	 */
	void deleteMarketActivityRemarkByActivityIds(String[] ids);

}
