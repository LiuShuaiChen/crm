package com.bjpowernode.crm.workbench.clue.remark.service.impl;

import java.util.List;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.clue.remark.dao.ClueRemarkDao;
import com.bjpowernode.crm.workbench.clue.remark.domain.ClueRemark;
import com.bjpowernode.crm.workbench.clue.remark.service.ClueRemarkService;

public class ClueRemarkServiceImpl implements ClueRemarkService {
	
	private ClueRemarkDao clueRemarkDao = SqlSessionutils.getSession().getMapper(ClueRemarkDao.class);

	@Override
	public int createClueRemark(ClueRemark clueRemark) {
		return clueRemarkDao.createClueRemark(clueRemark);
	}

	@Override
	public List<ClueRemark> ListingClueRemark(String clueId) {
		// TODO Auto-generated method stub
		return clueRemarkDao.ListingClueRemark(clueId);
	}
	
	public static void main(String[] args) {
		List<ClueRemark> list =  new ClueRemarkServiceImpl().ListingClueRemark("8da8021cebeb4b83a1974a611ae71934");
		for (ClueRemark clueRemark : list) {
			System.err.println(clueRemark.toString());
		}
	}
}
