package com.bjpowernode.crm.workbench.clue.service.impl;

import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.utils.UUIDutils;
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

//	public static void main(String[] args) {
////		Clue clue = new Clue();
////		clue.setId(UUIDutils.getUUid());
////		clue.setOwner("aa");
////		clue.setCompany("aa");
////		clue.setFullName("aa");
////		clue.setCity("aaa");
////		clue.setAppellation("aa");
////		clue.setCreateBy("aaa");
////		clue.setCountry("china");
//		
//		Clue clue = new Clue(UUIDutils.getUUid(), "aa", "aa", "aa", "aa", "aa", "aa", 11, 22, "aa", "aa", "aa", "aaa", "aaa", "aa", "sa", "aa", "aa", "dssads", "aa", "aa", "aa", "aa", "2017年7月4日", "dsa", "2017年7月4日09:50:49", "aa", "aa");
//		
//		ClueService clueService = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
//		int ret = clueService.createClue(clue);
//		System.out.println(ret);
//		
//		
//	}

	

}
