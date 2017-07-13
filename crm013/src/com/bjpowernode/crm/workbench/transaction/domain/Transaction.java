package com.bjpowernode.crm.workbench.transaction.domain;

/**
 * 交易 实体类 领域模型
 * @author LauShuaichen
 *
 */
public class Transaction {

	private String id;
	private String owner;
	private int amountOfMoney;
	private String name;
	private String expectedClosingDate;
	private String customerId;
	private String stage;
	private String type;
	private String source;
	private String activityId;
	private String contactsId;
	private String description;
	private String createBy;
	private String createTime;
	private String editBy;
	private String editTime;
	private String contactSummary;
	private String nextContactTime;

	private int possibility;

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

	public int getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(int amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpectedClosingDate() {
		return expectedClosingDate;
	}

	public void setExpectedClosingDate(String expectedClosingDate) {
		this.expectedClosingDate = expectedClosingDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getContactsId() {
		return contactsId;
	}

	public void setContactsId(String contactsId) {
		this.contactsId = contactsId;
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

	public String getContactSummary() {
		return contactSummary;
	}

	public void setContactSummary(String contactSummary) {
		this.contactSummary = contactSummary;
	}

	public String getNextContactTime() {
		return nextContactTime;
	}

	public void setNextContactTime(String nextContactTime) {
		this.nextContactTime = nextContactTime;
	}

	public int getPossibility() {
		return possibility;
	}

	public void setPossibility(int possibility) {
		this.possibility = possibility;
	}
	
	
	
}
