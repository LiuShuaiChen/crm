package com.bjpowernode.crm.workbench.activity.domain;

/**
 * 数据库 tbl_marketing_activities
 * 
 * @author LauShuaichen
 *
 */
public class Activity {

	private String id; // 字段id
	private String owner; // 字段owner
	private String type; // 字段type
	private String name; // 字段name
	private String state; // 字段state
	private String startDate; // 字段startDate
	private String endDate; // 字段endDate
	private int budgetCost; // 字段budgetCost
	private int actualCost; // 字段actualCost
	private String description; // 字段description
	private String createBy; // 字段createBy
	private String createTime; // 字段createTime
	private String editBy; // 字段editBy
	private String editTime; // 字段editTime

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

	public int getBudgetCost() {
		return budgetCost;
	}

	public void setBudgetCost(int budgetCost) {
		this.budgetCost = budgetCost;
	}

	public int getActualCost() {
		return actualCost;
	}

	public void setActualCost(int actualCost) {
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
