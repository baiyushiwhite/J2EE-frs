package edu.nju.frs.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonHandle {

	public static String getNowDate (){
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //设置日期格式
		return sdDateFormat.format(new Date());// new Date()为获取当前系统时间
	}
}
