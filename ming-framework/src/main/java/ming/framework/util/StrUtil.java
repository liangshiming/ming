package ming.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 * Created by Jrick on 2018/1/11.
 */
public class StrUtil {
	private static Pattern linePattern = Pattern.compile("_(\\w)");
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

	/**
	 * 下划线转驼峰
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);

		str = sb.toString();
		str = str.substring(0, 1).toUpperCase() + str.substring(1);

		return str;
	}
}
