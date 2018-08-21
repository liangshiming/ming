package ming.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

	public static final String DATE = "yyyyMMdd";
	public static final String DATE_TIME = "yyyyMMddHHmmss";

	public static String getCurrentDateTimeStr() {
		return format(new Date(), DATE_TIME);
	}

	public static Date parseDate(String dateStr, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = DATE;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			log.error("字符串转换日期异常", e);
		}
		return date;
	}

	public static Date parseDateTime(String dateStr, String pattern) {
		if (StringUtils.isEmpty(pattern)) {
			pattern = DATE_TIME;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			log.error("字符串转换日期时间异常", e);
		}
		return date;
	}

	public static String format(Date date, String pattern) {
		return date == null ? null : DateFormatUtils.format(date, pattern);
	}

}

