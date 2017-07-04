package com.bjpowernode.crm.workbench.clue.service;

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

}
