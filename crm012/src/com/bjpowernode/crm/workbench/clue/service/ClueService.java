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

	/**
	 * 根据id  获取线索 转发到详细页中
	 * @param id
	 * @return
	 */
	Clue queryClueForDetail(String id);

	/**
	 * 根据id 获取线索 转发到 修改模态窗口中
	 * @param id
	 * @return
	 */
	Clue editClueById(String id);

	/**
	 * 线索首页  更新 线索
	 * @return
	 */
	int updateClue(Clue clue);

}
