package com.bjpowernode.crm.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

/**
 * 动态代理 实现事务管理 与业务逻辑分离
 * 
 * @author LauShuaichen
 *
 */
public class TranscationInvocationHandler implements InvocationHandler {

	private Object target;

	public TranscationInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object = null;
		SqlSession sqlSession = null;

		try {
			sqlSession = SqlSessionutils.getSession();

			object = method.invoke(target, args);

			sqlSession.commit();
		} catch (Exception e) {
			try {
				sqlSession.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			SqlSessionutils.close(sqlSession);
		}

		return object;
	}
	
	public Object getProxy(){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

}
