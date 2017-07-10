package com.bjpowernode.crm.workbench.contacts.remark.service.impl;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.contacts.remark.dao.ContactRemarkDao;
import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;
import com.bjpowernode.crm.workbench.contacts.remark.service.ContactRemarkService;

public class ContactRemarkServiceImpl implements ContactRemarkService {
	
	private ContactRemarkDao contactRemarkDao = SqlSessionutils.getSession().getMapper(ContactRemarkDao.class);

	/**
	 * 创建联系人备注
	 */
	@Override
	public int CreateContactsRemark(ContactsRemark contactsRemark) {
		return contactRemarkDao.CreateContactsRemark(contactsRemark);
	}

	/**
	 * 获取联系人备注
	 */
	@Override
	public ContactsRemark editContactsRemark(String id) {
		return contactRemarkDao.editContactsRemark(id);
	}


	/**
	 * 更新联人备注
	 */
	@Override
	public int updateContactsRemaerk(ContactsRemark contactsRemark) {
		return contactRemarkDao.updateContactsRemaerk(contactsRemark);
	}

	/**
	 * 删除备注
	 */
	@Override
	public int deleteContactsRemark(String id) {
		return contactRemarkDao.deleteContactsRemark(id);
	}
	
	

}
