package com.bjpowernode.crm.workbench.activity.service;

import java.util.List;
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

	/**
	 * 根据id 删除市场huod
	 * @param ids
	 * @return
	 */
	int deleteMarketActivityByIds(String[] ids);

	/**
	 * 根据id查询市场活动
	 * @param id
	 * @return
	 */
	MarketActivity queryMarketActivityById(String id);

	/**
	 * 更新市场活动
	 * @param marketActivity
	 * @return
	 */
	int updateEditMarketActivity(MarketActivity marketActivity);

	/**
	 * 根据id 查找市场活动 明细
	 * @param id
	 * @return
	 */
	MarketActivity queryMarketActivityForDetailById(String id);

	/**
	 * 保存 修改市场活动明细页
	 * @param id
	 * @return
	 */
	MarketActivity updateEditMarketActivityDetail(String id);

	/**
	 * 根据条件查询市场活动 列表 做导出excel
	 * @param map
	 * @return
	 */
	List<MarketActivity> queryMarketActivityByCondition(Map<String, Object> map);
}
