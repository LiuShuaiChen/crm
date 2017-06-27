package com.bjpowernode.crm.utils;

/**
 * 分页查询 封装类
 * 
 * @author LauShuaichen
 *
 */
public class PageUtils {

	private int pageNo;
	private int pageCount;
	private int pageSize;
	private int total;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PageUtils() {
		super();
	}

	public PageUtils(int pageNo, int pageCount, int pageSize, int total) {
		super();
		this.pageNo = pageNo;
		this.pageCount = pageCount;
		this.pageSize = pageSize;
		this.total = total;
	}

}
