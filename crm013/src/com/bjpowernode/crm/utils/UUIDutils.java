package com.bjpowernode.crm.utils;

import java.util.UUID;

/**
 * uuid tools
 * @author LauShuaichen
 *
 */
public class UUIDutils {

	// 不允许私有化
	private UUIDutils() {
		super();
	}
	
	/**
	 * 类调用 uuid生成
	 * @return
	 */
	public static String getUUid(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getUUid());
	}
}
