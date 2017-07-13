package com.bjpowernode.crm.workbench.contacts.service.impl;

import java.util.List;
import java.util.Map;

import com.bjpowernode.crm.commons.vo.PaginationVO;
import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.contacts.dao.ContactsDao;
import com.bjpowernode.crm.workbench.contacts.domain.Contacts;
import com.bjpowernode.crm.workbench.contacts.service.ContactsService;

public class ContactsServiceImpl implements ContactsService {
	
	private ContactsDao contactsDao = SqlSessionutils.getSession().getMapper(ContactsDao.class);

	/**
	 * 持久化对象 联系人
	 */
	@Override
	public int createContacts(Contacts contacts) {
		return contactsDao.createContacts(contacts);
	}

	/**
	 * 根据id 查询联系人
	 */
	@Override
	public Contacts EditContactsGetById(String id) {
		return contactsDao.EditContactsGetById(id);
	}

	/**
	 * 更新联系人
	 */
	@Override
	public int updateContacts(Contacts contacts) {
		return contactsDao.updateContacts(contacts);
	}

	/**
	 * 删除联系人
	 */
	@Override
	public int deleteContacts(String id) {
		return contactsDao.deleteContacts(id);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PaginationVO<Contacts> listingContacts(Map<String, Object> map) {
		//查询记录列表
		List<Contacts> contactsList = contactsDao.listingContactsForPage(map);
		//查询记录总数
		long totalCount = contactsDao.listingContactsForTotal(map);
		
		PaginationVO<Contacts> vo = new PaginationVO<>();
		vo.setDataList(contactsList);
		vo.setTotalCount(totalCount);
		
		return vo;
	}

}
