package com.bjpowernode.crm.workbench.contacts.remark.service;

import com.bjpowernode.crm.workbench.contacts.remark.domain.ContactsRemark;

public interface ContactRemarkService {

	int CreateContactsRemark(ContactsRemark contactsRemark);

	ContactsRemark editContactsRemark(String id);

	int updateContactsRemaerk(ContactsRemark contactsRemark);

	int deleteContactsRemark(String id);

}
