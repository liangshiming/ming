package ming.framework.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * Created by Jrick on 2018/1/11.
 */
@Slf4j
public class ReflectUtil {

	public static Map<String, String> toMap(Object obj) {
		Map<String, String> paramMap = null;
		try {
			paramMap = BeanUtils.describe(obj);
		} catch (Exception e) {
			ExceptionUtil.logAndThrow(log, "反射对象到map发生异常", e);
		}
		return paramMap;
	}

	public static void copy(Object orig, Object dest) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			ExceptionUtil.logAndThrow(log, "复制对象全部属性发生异常", e);
		}
	}

	public static void copy(Object orig, Object dest, String... fields) {
		for (String field : fields) {
			try {
				BeanUtils.copyProperty(dest, field, getFieldValue(orig, field));
			} catch (Exception e) {
				ExceptionUtil.logAndThrow(log, "复制对象属性发生异常", e);
			}
		}
	}

	public static void setFiledValue(Object object, String field, Object value) {
		try {
			BeanUtils.setProperty(object, field, value);
		} catch (Exception e) {
			ExceptionUtil.logAndThrow(log, "设置对象单个属性值发生异常", e);
		}
	}

	public static String getFieldValue(Object object, String field) {
		String fieldValue = null;
		try {
			fieldValue = BeanUtils.getProperty(object, field);
		} catch (Exception e) {
			ExceptionUtil.logAndThrow(log, "获取对象单个属性值发生异常", e);
		}
		return fieldValue;
	}

	@SuppressWarnings("rawtypes")
	public static Method getMethod(Class clazz, String methodName) {
		Class[] paramTypeArr = null;
		Method method = ReflectionUtils.findMethod(clazz, methodName, paramTypeArr);
		return method;
	}

	public static Object invoke(Object obj, String methodName, Object param) {
		return ReflectionUtils.invokeMethod(getMethod(obj.getClass(), methodName), obj, param);
	}

	public static Object invoke(Object obj, String methodName) {
		return ReflectionUtils.invokeMethod(getMethod(obj.getClass(), methodName), obj);
	}

	public static <T> Map<String, T> reflectToMap(Collection<T> list, String... field) {
		Map<String, T> map = new TreeMap<>();
		for (T t : list) {
			String[] fieldValueArr = new String[field.length];
			for (int i = 0; i < field.length; i++) {
				fieldValueArr[i] = getFieldValue(t, field[i]);
			}
			String key = StrUtil.join(fieldValueArr);
			map.put(key, t);
		}
		return map;
	}

	public static <T> Map<String, Map<String, T>> reflectToDuplicateMap(Collection<T> list, String[] outFieldArr,
			String[] inFieldArr) {
		Map<String, Map<String, T>> outMap = new TreeMap<>();
		for (T t : list) {
			String[] outFieldValueArr = new String[outFieldArr.length];
			for (int i = 0; i < outFieldArr.length; i++) {
				outFieldValueArr[i] = getFieldValue(t, outFieldArr[i]);
			}
			String outKey = StrUtil.join(outFieldValueArr);
			Map<String, T> inMap = outMap.get(outKey);
			if (inMap == null) {
				inMap = new TreeMap<>();
				outMap.put(outKey, inMap);
			}

			String[] inFieldValueArr = new String[inFieldArr.length];
			for (int i = 0; i < inFieldArr.length; i++) {
				inFieldValueArr[i] = getFieldValue(t, inFieldArr[i]);
			}
			String inKey = StrUtil.join(inFieldValueArr);

			inMap.put(inKey, t);
		}
		return outMap;
	}

	public static <T> List<T> filterList(Collection<T> list, String field, String... value) {
		if (CollectionUtil.isEmpty(list) || StringUtils.isBlank(field) || ArrayUtils.isEmpty(value)) {
			return null;
		}
		List<T> listTemp = new ArrayList<>();
		for (T obj : list) {
			if (ArrayUtils.contains(value, getFieldValue(obj, field))) {
				listTemp.add(obj);
			}
		}
		return listTemp;
	}
}
