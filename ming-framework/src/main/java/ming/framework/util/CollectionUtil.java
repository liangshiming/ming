package ming.framework.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.Map;
/**
 * Created by Jrick on 2018/1/11.
 */
@SuppressWarnings("rawtypes")
public class CollectionUtil {
	public static boolean isEmpty(Collection collection) {
		return CollectionUtils.isEmpty(collection);
	}

	public static boolean isNotEmpty(Collection collection) {
		return CollectionUtils.isNotEmpty(collection);
	}

	public static boolean isEmpty(Map map) {
		return MapUtils.isEmpty(map);
	}

	public static boolean isNotEmpty(Map map) {
		return MapUtils.isNotEmpty(map);
	}

	public static String getStringValue(Map map, String key) {
		return MapUtils.getString(map, key);
	}

	public static String getStringValue(Map map, String key, String defaultValue) {
		return MapUtils.getString(map, key, defaultValue);
	}

	public static long getLongValue(Map map, String key) {
		return MapUtils.getLongValue(map, key);
	}

	public static long getLongValue(Map map, String key, long defaultValue) {
		return MapUtils.getLongValue(map, key, defaultValue);
	}

	public static int getIntValue(Map map, String key) {
		return MapUtils.getIntValue(map, key);
	}

	public static int getIntValue(Map map, String key, int defaultValue) {
		return MapUtils.getIntValue(map, key, defaultValue);
	}

	public static Object getValue(Map map, String key) {
		return MapUtils.getObject(map, key);
	}

	public static Object geValue(Map map, String key, Object defaultValue) {
		return MapUtils.getObject(map, key, defaultValue);
	}

	public static boolean contain(String[] arr, String o) {
		if (arr == null || arr.length == 0 || o == null) {
			return false;
		}
		for (String obj : arr) {
			if (o.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public static boolean notContain(String[] arr, String o) {
		return !contain(arr, o);
	}

	public static boolean containIgnoreCase(String[] arr, String o) {
		if (arr == null || arr.length == 0 || o == null) {
			return false;
		}
		for (String obj : arr) {
			if (o.equalsIgnoreCase(obj)) {
				return true;
			}
		}
		return false;
	}

	public static boolean notContainIgnoreCase(String[] arr, String o) {
		return !containIgnoreCase(arr, o);
	}

	public static boolean contain(int[] arr, int o) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		for (int obj : arr) {
			if (o == obj) {
				return true;
			}
		}
		return false;
	}

	public static boolean notContain(int[] arr, int o) {
		return !contain(arr, o);
	}
}
