package ming.framework.validate;

import ming.framework.constant.errcode.ErrorCode;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.exception.SystemException;
import org.apache.commons.lang3.StringUtils;

public class Validator {

	public static void notBlank(String param, ErrorCode errorCode,String... paramArr) {
		if (param == null || param.trim().length() == 0) {
			throw new SystemException(errorCode,paramArr);
		}
	}

	public static void notMatch(String param, String pattern, ErrorCode errorCode, String... paramArr) {
		if(!param.matches(pattern)){
			throw new SystemException(errorCode,paramArr);
		}
	}
	public static void notEqual(String param1, String param2, ErrorCode errorCode, String... paramArr) {
		if(!StringUtils.equals(param1,param2)){
			throw new SystemException(errorCode,paramArr);
		}
	}

	public static void isEqual(String param1, String param2, ErrorCode errorCode, String... paramArr) {
		if(!StringUtils.equals(param1,param2)){
			throw new SystemException(errorCode,paramArr);
		}
	}

	public static void notNull(Object obj, ErrorCode errorCode,String... paramArr) {
		if (obj == null) {
			throw new SystemException(errorCode,paramArr);
		}
	}

	public static void isNull(Object obj, ErrorCode errorCode,String... paramArr) {
		if (obj != null) {
			throw new SystemException(errorCode,paramArr);
		}
	}

}
