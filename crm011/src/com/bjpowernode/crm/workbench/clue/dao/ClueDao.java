package com.bjpowernode.crm.workbench.clue.dao;

import com.bjpowernode.crm.workbench.clue.domain.Clue;

public interface ClueDao {

	/**
	 * 创建新线索
	 * @param clue
	 * @return
	 */
	int createClue(Clue clue);
}
