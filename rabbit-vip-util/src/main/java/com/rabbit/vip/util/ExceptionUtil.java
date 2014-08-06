package com.rabbit.vip.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author dengziwu
 *  
 *  public static void main(String[] args) {
		String a=null;
		try{
			Long.parseLong(a);
		}catch(Exception e){
			System.out.println(getExceptionInfo(e));
		}
	}
	输出异常
 *  
 *  
 *  
 */
public class ExceptionUtil {

	public static String getExceptionInfo(Throwable e) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		return writer.toString();
	}

	public static String getExceptionInfoForMail(Throwable e) {
		String content = "<br/>" + ExceptionUtil.getExceptionInfo(e);
		content = content.replaceAll("\n", "").replaceAll("\r", "<br/>");
		return content;
	}

}
