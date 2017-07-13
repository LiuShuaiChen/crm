package com.bjpowernode.crm.workbench.contacts.dao;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.workbench.contacts.domain.Contacts;

public interface ContactsDao {

	int createContacts(Contacts contacts);

	Contacts EditContactsGetById(String id);

	int updateContacts(Contacts contacts);

	int deleteContacts(String id);

	List<Contacts> listingContactsForPage(Map<String, Object> map);

	long listingContactsForTotal(Map<String, Object> map);

}
