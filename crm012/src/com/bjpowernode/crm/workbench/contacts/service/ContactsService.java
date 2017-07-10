package com.bjpowernode.crm.workbench.contacts.service;

import com.bjpowernode.crm.workbench.contacts.domain.Contacts;

public interface ContactsService {

	int createContacts(Contacts contacts);

	Contacts EditContactsGetById(String id);

	int updateContacts(Contacts contacts);

	int deleteContacts(String id);

}
