package com.bjpowernode.crm.security.exceptions;

/**
 * 自定义异常
 * 
 * @author LauShuaichen
 *
 */
public class ApplicationException extends RuntimeException {

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message) {
		super(message);
	}

}
