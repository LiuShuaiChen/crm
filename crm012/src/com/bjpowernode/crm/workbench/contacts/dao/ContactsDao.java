package com.bjpowernode.crm.workbench.contacts.dao;

import com.bjpowernode.crm.workbench.contacts.domain.Contacts;

public interface ContactsDao {

	int createContacts(Contacts contacts);

	Contacts EditContactsGetById(String id);

	int updateContacts(Contacts contacts);

	int deleteContacts(String id);

}
