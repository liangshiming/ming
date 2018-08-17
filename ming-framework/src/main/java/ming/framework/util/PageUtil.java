package ming.framework.util;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class PageUtil {
	public static final String DEFAULT_PAGE_SIZE = "50";
	public static final String DEFAULT_PAGE_NUM = "1";

	public static void setPaging(String pageNum, String pageSize) {
		String pageNumTemp = StringUtils.defaultIfBlank(pageNum, DEFAULT_PAGE_NUM);
		String pageSizeTemp = pageSize;
		if (pageSizeTemp == null || "0".equals(pageSizeTemp)) {
			pageSizeTemp = DEFAULT_PAGE_SIZE;
		}
		PageHelper.startPage(NumberUtils.toInt(pageNumTemp), NumberUtils.toInt(pageSizeTemp));
	}
}
