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

	/**
	 * 根据id查找市场活动 返回datail
	 * @param id
	 * @return
	 */
	MarketActivity queryMarketActivityById(String id);

	/**
	 * 保存修改的市场活动信息
	 * @param marketActivity
	 * @return
	 */
	int updateEditMarketActivity(MarketActivity marketActivity);

	/**
	 * 根据id获取市场活动详情信息
	 * @param id
	 * @return
	 */
	MarketActivity queryMarketActivityForDetailById(String id);

	/**
	 * 根据id 获取到市场活动明细 铺到 市场活动明细页中的编辑模态窗口中
	 * @param marketActivity
	 * @return
	 */
	MarketActivity updateEditMarketActivityDetail(String id);

	/**
	 * 根据条件查询市场活动
	 * @param map
	 * @return
	 */
	List<MarketActivity> queryMarketActivityByCondition(Map<String, Object> map);


	
}
