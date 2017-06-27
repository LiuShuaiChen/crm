package com.bjpowernode.crm.utils;

/**
 * 业务逻辑分层 调用动态代理

 * @author LauShuaichen
 *
 */
public class ServiceFactory {

	public static Object getService(Object service){
		return new TranscationInvocationHandler(service).getProxy();
	}
}
