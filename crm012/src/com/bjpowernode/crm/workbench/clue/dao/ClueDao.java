package com.bjpowernode.crm.workbench.clue.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.workbench.clue.domain.Clue;

public interface ClueDao {

	/**
	 * 创建新线索
	 * @param clue
	 * @return
	 */
	int createClue(Clue clue);

	/**
	 * 查询 所有记录
	 * @param map
	 * @return
	 */
	List<Clue> queryClueForPageByCondition(Map<String, Object> map);

	/**
	 * 查询 记录总数
	 * @param map
	 * @return
	 */
	long queryTotalCountOfClueForPageByCondition(Map<String, Object> map);

	/**
	 * 获取id 查看线索明细
	 * @param id
	 * @return
	 */
	Clue queryClueForDetail(String id);

}
