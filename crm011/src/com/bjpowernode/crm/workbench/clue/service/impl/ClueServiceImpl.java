package com.bjpowernode.crm.workbench.clue.service.impl;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.clue.dao.ClueDao;
import com.bjpowernode.crm.workbench.clue.domain.Clue;
import com.bjpowernode.crm.workbench.clue.service.ClueService;

/**
 * 线索 业务处理接口实现类
 * @author LauShuaichen
 *
 */
public class ClueServiceImpl implements ClueService {
	
	private ClueDao clueDao = SqlSessionutils.getSession().getMapper(ClueDao.class);

	@Override
	public int createClue(Clue clue) {
		return clueDao.createClue(clue);
	}

	

	

}
