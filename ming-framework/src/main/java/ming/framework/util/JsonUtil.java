package ming.framework.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Json工具类
 * Created by Jrick on 2018/1/1.
 */
@Slf4j
public class JsonUtil {

    private static final String ERR_TEXT_TO_OBJ = "转化json到对象发生异常";
    private static final String ERR_TEXT_TO_JSON = "转化对象到json发生异常";

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static String toJson(Object obj) {
        String s = null;
        try {
            s = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            ExceptionUtil.logAndThrow(log, ERR_TEXT_TO_JSON, e);
        }
        return s;
    }

    public static <T> T toObj(String s, Class<T> beanClass) {
        T obj = null;
        try {
            obj = mapper.readValue(s, beanClass);
        } catch (Exception e) {
            ExceptionUtil.logAndThrow(log, ERR_TEXT_TO_OBJ, e);
        }
        return obj;
    }

    public static Object toObj(String s, String pkg) {
        Object obj = null;
        try {
            obj = mapper.readValue(s, Class.forName(pkg));
        } catch (Exception e) {
            ExceptionUtil.logAndThrow(log, ERR_TEXT_TO_OBJ, e);
        }
        return obj;
    }

    public static <T> T toObj(String s, JavaType beanClass) {
        T obj = null;
        try {
            obj = mapper.readValue(s, beanClass);
        } catch (Exception e) {
            ExceptionUtil.logAndThrow(log, ERR_TEXT_TO_OBJ, e);
        }
        return obj;
    }

    public static String getJsonStr(String jsonStr, String jsonpCallBack) {
        String str = jsonStr;
        if (jsonpCallBack != null && jsonpCallBack.length() > 0) {
            str = jsonpCallBack + "(" + str + ")";
        }
        return str;

    }
}