package com.lolforum.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.lolforum.constant.Constant;

/**
 * 通用工具类
 */
public class Util {
	/**
	 * 得到访问者真实IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		if ("127.0.0.1".equals(ip))
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		return ip;
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return true：为空； false：非空
	 */
	public static boolean isNull(String str) {
		if (str != null && !str.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 清除字符串中所有html标签
	 * 
	 * @param html
	 * @return
	 */
	public static String clearHtmlTags(String html) {
		return html.replaceAll("<[^>]+>", "");
	}
	
	/**
	 * MD5 加密
	 */
	public static String encryptMD5(String str) {
		MessageDigest messageDigest = null;
		
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			
			messageDigest.reset();
			
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte[] byteArray = messageDigest.digest();
		
		StringBuffer md5StrBuff = new StringBuffer();
		
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		
		return md5StrBuff.toString();
	}
	
	/**
	 * 得到抛异常的信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}
	
	/**
	 * 得到时间的精简式
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getSimpleTimeStr(Date sourceTime) {
		Date now = new Date();
		Calendar nowCl = Calendar.getInstance();
		nowCl.setTime(now);
		Calendar sourceCl = Calendar.getInstance();
		sourceCl.setTime(sourceTime);
		
		if (sourceTime instanceof Timestamp) {
			if (nowCl.get(Calendar.DAY_OF_MONTH) == sourceCl
					.get(Calendar.DAY_OF_MONTH)) {
				return new SimpleDateFormat("HH:mm").format(sourceTime);
			} else if (nowCl.get(Calendar.YEAR) == sourceCl.get(Calendar.YEAR)) {
				return new SimpleDateFormat("MM-dd").format(sourceTime);
			} else {
				return new SimpleDateFormat("yyyy-MM").format(sourceTime);
			}
		} else if (sourceTime instanceof Date || sourceTime instanceof java.sql.Date) {
			if (nowCl.get(Calendar.YEAR) == sourceCl.get(Calendar.YEAR)) {
				return new SimpleDateFormat("MM-dd").format(sourceTime);
			} else {
				return new SimpleDateFormat("yyyy-MM").format(sourceTime);
			}
		}
		return "Error";
	}
	
	/**
	 * 得到当前系统日期
	 * 
	 * @return
	 */
	public static String getTodayDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
	 * 得到当前系统时间
	 * 
	 * @return
	 */
	public static String nowTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
	}
	
	/**
	 * 获取头像全路径
	 * @param avatar
	 * @return
	 */
	public static String realAvatarUrl(String avatar) {
		return String.format("%s/%s", Constant.ASSETS_AVATAR_URL, avatar);
	}
	
}
