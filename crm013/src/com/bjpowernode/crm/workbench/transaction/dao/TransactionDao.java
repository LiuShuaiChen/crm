package com.bjpowernode.crm.workbench.transaction.dao;

import com.bjpowernode.crm.workbench.transaction.domain.Transaction;

public interface TransactionDao {

	int createTransaction(Transaction transaction);

}
