package com.bjpowernode.crm.workbench.activity.service;

import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;

/**
 * 市场活动 业务处理接口
 * @author LauShuaichen
 *
 */
public interface MarketActivityService {
	
	/**
	 * 保存新创建的市场活动
	 * @param marketActivity
	 * @return
	 */
	int insertCreateMarketActivity(MarketActivity marketActivity);

	/**
	 * 根据条件查询市场活动
	 * @param map
	 * @return
	 */
	PaginationVO<MarketActivity> queryMarketActivityForPageByCondition(Map<String, Object>map);

	int deleteMarketActivityByIds(String[] ids);

	MarketActivity queryMarketActivityById(String string);

	int updateEditMarketActivity(MarketActivity marketActivity);

	MarketActivity queryMarketActivityForDetailById(String id);

	/**
	 * 保存 修改市场活动明细页
	 * @param id
	 * @return
	 */
	MarketActivity updateEditMarketActivityDetail(String id);
}
