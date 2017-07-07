package com.bjpowernode.crm.workbench.clue.service;

import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.workbench.clue.domain.Clue;

/**
 * 线索 业务处理接口
 * @author LauShuaichen
 *
 */
public interface ClueService {

	/**
	 * 创建新线索
	 * @param clue
	 * @return
	 */
	int createClue(Clue clue);

	/**
	 * 分页查询线索
	 * @param map
	 * @return
	 */
	PaginationVO<Clue> queryClueForPageByCondition(Map<String, Object> map);

	Clue queryClueForDetail(String id);

}
