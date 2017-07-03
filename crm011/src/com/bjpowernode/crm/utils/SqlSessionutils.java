package com.bjpowernode.crm.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * mybatis 数据库utils
 * 
 * @author LauShuaichen
 *
 */
public class SqlSessionutils {

	private static SqlSessionFactory sqlSessionFactory = null;
	private static ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();

	static {

		String resource = "mybatis.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	/**
	 * 获取db
	 * 
	 * @return
	 */
	public static SqlSession getSession() {
		SqlSession sqlSession = t.get();
		if (sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession();
			t.set(sqlSession);
		}
		return sqlSession;
	}

	/**
	 * 关闭db
	 * 
	 * @param session
	 */
	public static void close(SqlSession session) {
		if (session != null) {
			session.close();
			t.remove();
		}
	}
	

/*	public static void main(String[] args) {
		System.out.println(SqlSessionutils.getSession());
	}*/
}
