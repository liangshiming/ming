package ming.framework.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
/**
 * Created by Jrick on 2018/1/11.
 */
public class StrUtil {
	public static final String SPACE = " ";
	public static final String EMPTY = "";
	public static final String SPLITER = "###";

	private static final String YES = "1";
	private static final String NO = "0";

	public static String join(String... str) {
		StringBuilder sb = new StringBuilder();
		for (String s : str) {
			sb.append(SPLITER).append(s);
		}
		return sb.toString().replaceFirst(SPLITER, "");
	}
	public static List<String> split(String str,String spliter) {
		List<String> list = new ArrayList<>();
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] strArr = str.split(spliter);
		if (strArr.length == 0) {
			return null;
		}
		for (String s : strArr) {
			if (StringUtils.isBlank(s)) {
				continue;
			}
			list.add(s);
		}
		return list;
	}

	public static String convertSwitch(boolean bool) {
		return bool ? YES : NO;
	}

	public static boolean convertSwitch(String str) {
		return YES.equals(str);
	}
}
