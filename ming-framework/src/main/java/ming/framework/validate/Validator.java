package ming.framework.validate;

import ming.framework.constant.errcode.ErrorCode;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.exception.SystemException;

public class Validator {

	public static void notBlank(String param, ErrorCode errorCode,String... paramArr) {
		if (param == null || param.trim().length() == 0) {
			throw new SystemException(errorCode,paramArr);
		}
	}

	public static void notMatch(String param, String pattern, ErrorCodeSys errorCode, String... paramArr) {
		if(param.matches(pattern)){
			throw new SystemException(errorCode,paramArr);
		}
	}
}
