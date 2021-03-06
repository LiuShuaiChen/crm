package com.bjpowernode.crm.workbench.clue.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.utils.UUIDutils;
import com.bjpowernode.crm.workbench.clue.dao.ClueDao;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;

/**
 * 线索 业务处理接口实现类
 * 
 * @author LauShuaichen
 *
 */
public class ClueServiceImpl implements ClueService {

	private ClueDao clueDao = SqlSessionutils.getSession().getMapper(ClueDao.class);

	/**
	 * 创建新线索
	 */
	@Override
	public int createClue(Clue clue) {
		return clueDao.createClue(clue);
	}

	/**
	 * 线索首页 分页查询
	 */
	@Override
	public PaginationVO<Clue> queryClueForPageByCondition(Map<String, Object> map) {
		// 调用dao 查询记录列表
		List<Clue> clueList = clueDao.queryClueForPageByCondition(map);
		// 调用dao 查询记录总数
		long totalCount = clueDao.queryTotalCountOfClueForPageByCondition(map);

		// 把clueList totalCount转换成vo
		PaginationVO<Clue> vo = new PaginationVO<Clue>();
		vo.setDataList(clueList);
		vo.setTotalCount(totalCount);
		return vo;
	}

	/**
	 * 查看线索明细
	 */
	@Override
	public Clue queryClueForDetail(String id) {
		return clueDao.queryClueForDetail(id);
	}

	/**
	 * 根据id 获取线索 准备进行修改
	 */
	@Override
	public Clue editClueById(String id) {
		// TODO Auto-generated method stub
		return clueDao.editClueById(id);
	}

	/**
	 * 更新线索信息
	 */
	@Override
	public int updateClue(Clue clue) {
		// TODO Auto-generated method stub
		return clueDao.updateClue(clue);
	}

}
