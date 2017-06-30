package com.bjpowernode.crm.workbench.activity.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;

/**
 * MarketActivityDao
 * @author LauShuaichen
 *
 */
public interface MarketActivityDao {
	
	/**
	 * 保存市场活动案例
	 * @param marketActivity
	 * @return
	 */
	int insertCreateMarketActivity(MarketActivity marketActivity);

	/**
	 * 根据条件分页查询市场活动列表
	 * @param map
	 * @return
	 */
	List<MarketActivity> queryMarketActivityForPageByCondition(Map<String, Object>map);
	
	/**
	 * 根据条件分页查询市场活动记录
	 * @param map
	 * @return
	 */
	long queryTotalCountofMarketActivityByCondition(Map<String, Object>map);

	
	/**
	 * 根据查找id批量删除市场活动
	 * @param ids
	 * @return
	 */
	int deleteMarketActivityById(String[] ids);
	
}
