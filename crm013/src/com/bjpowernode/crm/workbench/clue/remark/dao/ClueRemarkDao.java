package com.bjpowernode.crm.workbench.clue.remark.dao;

import java.util.List;

import com.bjpowernode.crm.workbench.clue.remark.domain.ClueRemark;

public interface ClueRemarkDao {

	/**
	 * 添加新的线索 备注
	 * @param clueRemark
	 * @return
	 */
	int createClueRemark(ClueRemark clueRemark);

	/**
	 * 查询所有该线索的所有备注
	 * @param clueId
	 * @return
	 */
	List<ClueRemark> ListingClueRemark(String clueId);

}
