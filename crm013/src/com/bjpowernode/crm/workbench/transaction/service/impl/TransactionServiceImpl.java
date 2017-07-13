package com.bjpowernode.crm.workbench.transaction.service.impl;

import com.bjpowernode.crm.utils.SqlSessionutils;
import com.bjpowernode.crm.workbench.transaction.dao.TransactionDao;
import com.bjpowernode.crm.workbench.transaction.domain.Transaction;
import com.bjpowernode.crm.workbench.transaction.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	private TransactionDao transactionDao = SqlSessionutils.getSession().getMapper(TransactionDao.class);

	@Override
	public int CreateTransaction(Transaction transaction) {
		return transactionDao.createTransaction(transaction);
	}
	
	

}
