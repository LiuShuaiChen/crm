package com.bjpowernode.crm.commons.vo;

import java.util.List;

/**
 * 分页实体类
 * 
 * @author LauShuaichen
 *
 * @param <T>
 */
public class PainationVO<T> {

	private long totalCount;

	private List<T> dataList;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
