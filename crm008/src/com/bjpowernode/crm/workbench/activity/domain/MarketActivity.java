package com.bjpowernode.crm.workbench.activity.domain;

/**
 * 数据库 tbl_marketing_activities
 * 市场活动实体类
 * @author LauShuaichen
 *
 */
public class MarketActivity {

	private String id;
	private String owner;
	private String type;
	private String name;
	private String state;
	private String startDate;
	private String endDate;
	private long budgetCost;
	private long actualCost;
	private String description;
	private String createBy;
	private String createTime;
	private String editBy;
	private String editTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public long getBudgetCost() {
		return budgetCost;
	}
	public void setBudgetCost(long budgetCost) {
		this.budgetCost = budgetCost;
	}
	public long getActualCost() {
		return actualCost;
	}
	public void setActualCost(long actualCost) {
		this.actualCost = actualCost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEditBy() {
		return editBy;
	}
	public void setEditBy(String editBy) {
		this.editBy = editBy;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	

}
