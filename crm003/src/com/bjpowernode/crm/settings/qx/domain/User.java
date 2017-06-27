package com.bjpowernode.crm.settings.qx.domain;

/**
 * 对应数据表 tbl_user
 * 
 * @author LauShuaichen
 *
 */
public class User {

	private String id; // 字段id
	private String loginAct; // 字段loginAct
	private String loginPwd; // 字段loginPwd
	private String name; // 字段name
	private String email; // 字段email
	private String lockStatus; // 字段lockStatus
	private String expireTime; // 字段expireTime
	private String allowIps; // 字段allowIps
	private String deptName; // 字段deptName
	private String createBy; // 字段createBy
	private String createTime; // 字段createTime
	private String updateBy; // 字段updateBy
	private String updateTime; // 字段updateTime

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginAct() {
		return loginAct;
	}

	public void setLoginAct(String loginAct) {
		this.loginAct = loginAct;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getAllowIps() {
		return allowIps;
	}

	public void setAllowIps(String allowIps) {
		this.allowIps = allowIps;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public User() {
		super();
	}

	public User(String id, String loginAct, String loginPwd, String name, String email, String lockStatus,
			String expireTime, String allowIps, String deptName, String createBy, String createTime, String updateBy,
			String updateTime) {
		super();
		this.id = id;
		this.loginAct = loginAct;
		this.loginPwd = loginPwd;
		this.name = name;
		this.email = email;
		this.lockStatus = lockStatus;
		this.expireTime = expireTime;
		this.allowIps = allowIps;
		this.deptName = deptName;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

	public User(String loginAct, String loginPwd, String name, String email, String lockStatus, String expireTime,
			String allowIps, String deptName, String createBy, String createTime, String updateBy, String updateTime) {
		super();
		this.loginAct = loginAct;
		this.loginPwd = loginPwd;
		this.name = name;
		this.email = email;
		this.lockStatus = lockStatus;
		this.expireTime = expireTime;
		this.allowIps = allowIps;
		this.deptName = deptName;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
	}

	public User(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginAct=" + loginAct + ", loginPwd=" + loginPwd + ", name=" + name + ", email="
				+ email + ", lockStatus=" + lockStatus + ", expireTime=" + expireTime + ", allowIps=" + allowIps
				+ ", deptName=" + deptName + ", createBy=" + createBy + ", createTime=" + createTime + ", updateBy="
				+ updateBy + ", updateTime=" + updateTime + "]";
	}

	public User(String id, String loginAct, String loginPwd, String name, String email, String lockStatus,
			String expireTime, String allowIps, String deptName, String createBy, String createTime) {
		super();
		this.id = id;
		this.loginAct = loginAct;
		this.loginPwd = loginPwd;
		this.name = name;
		this.email = email;
		this.lockStatus = lockStatus;
		this.expireTime = expireTime;
		this.allowIps = allowIps;
		this.deptName = deptName;
		this.createBy = createBy;
		this.createTime = createTime;
	}

}
