package com.bjpowernode.crm.workbench.contacts.remark.dao;

import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;

public interface ContactRemarkDao {

	int CreateContactsRemark(ContactsRemark contactsRemark);

	ContactsRemark editContactsRemark(String id);

	int updateContactsRemaerk(ContactsRemark contactsRemark);

	int deleteContactsRemark(String id);

}
