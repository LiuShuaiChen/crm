package com.bjpowernode.crm.workbench.activity.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityDao;
import com.bjpowernode.crm.workbench.activity.dao.MarketActivityRemarkDao;
import com.bjpowernode.crm.workbench.activity.domain.MarketActivity;
import com.bjpowernode.crm.workbench.activity.service.MarketActivityService;

/**
 * 
 * @author LauShuaichen
 *
 */
public class MarketActivityServiceImpl implements MarketActivityService {

	private MarketActivityDao marketActivityDao = SqlSessionutils.getSession().getMapper(MarketActivityDao.class);
	
	private MarketActivityRemarkDao marketActivityRemarkDao = SqlSessionutils.getSession().getMapper(MarketActivityRemarkDao.class);

	/**
	 * 市场活动创建
	 */
	@Override
	public int insertCreateMarketActivity(MarketActivity marketActivity) {
		return marketActivityDao.insertCreateMarketActivity(marketActivity);
	}

	/**
	 * 根据条件分页查询市场活动
	 */
	@Override
	public PaginationVO<MarketActivity> queryMarketActivityForPageByCondition(Map<String, Object> map) {
		// 调用dao查询记录列表
		List<MarketActivity> activityList = marketActivityDao.queryMarketActivityForPageByCondition(map);
		Collections.sort(activityList);
		// 调用dao查询记录总数
		long totalCount = marketActivityDao.queryTotalCountofMarketActivityByCondition(map);

		// 把activityList和totalCount封装成paginationVO
		PaginationVO<MarketActivity> vo = new PaginationVO<MarketActivity>();
		vo.setDataList(activityList);
		vo.setTotalCount(totalCount);

		return vo;
	}

	/**
	 * 根据查找id删除市场活动
	 */
	@Override
	public int deleteMarketActivityByIds(String[] ids) {
		marketActivityRemarkDao.deleteMarketActivityRemarkByActivityIds(ids);
		return marketActivityDao.deleteMarketActivityById(ids);

	}

	@Override
	public MarketActivity queryMarketActivityById(String id) {

		return marketActivityDao.queryMarketActivityById(id);
	}

	@Override
	public int updateEditMarketActivity(MarketActivity marketActivity) {
		return marketActivityDao.updateEditMarketActivity(marketActivity);
	}

	/**
	 * 根据id 获取市场活动id put到 市场活动详情页
	 */
	@Override
	public MarketActivity queryMarketActivityForDetailById(String id) {
		return marketActivityDao.queryMarketActivityForDetailById(id);
	}

	@Override
	public MarketActivity updateEditMarketActivityDetail(String id) {
		return marketActivityDao.updateEditMarketActivityDetail(id);
	}

	@Override
	public List<MarketActivity> queryMarketActivityByCondition(Map<String, Object> map) {
		return marketActivityDao.queryMarketActivityByCondition(map);
	}

	/**
	 * 批量导入市场活动
	 */
	@Override
	public int saveCreateMarketActivityByList(List<MarketActivity> activityList) {
		return marketActivityDao.saveCreateMarketActivityByList(activityList);
	}

	/**
	 * 更新市场活动详情信息 详细页中的编辑按钮 出来的修改市场活动模态窗口 更新按钮
	 */
	@Override
	public int updateEditMarketActivityDetailByMarketActivity(MarketActivity marketActivity) {
		return marketActivityDao.updateEditMarketActivityDetailByMarketActivity(marketActivity);
	}

	@Override
	public int deleteMarketActivityDetail(String id) {
		marketActivityRemarkDao.deleteMarketActivityRemarkByActivityId(id);
		return marketActivityDao.deleteMarketActivityDetail(id);
	}

	/**
	 * 根据名称和clueId模糊查询市场活动
	 */
	@Override
	public List<MarketActivity> bundClueMarketActivity(Map<String, Object> map) {
		return marketActivityDao.bundClueMarketActivity(map);
	}


	
	
}
