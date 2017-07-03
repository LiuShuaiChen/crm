package com.bjpowernode.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date tools 时间工具
 * @author LauShuaichen
 *
 */
public class DateUtils {
	
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	//不允许实例化
	private DateUtils() {
	}

	/**
	 * 获取当前时间 格式为yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String getDate() {
		return simpleDateFormat.format(new Date());
	}

	public static void main(String[] args) {
			System.out.println(getDate());
	}

}
