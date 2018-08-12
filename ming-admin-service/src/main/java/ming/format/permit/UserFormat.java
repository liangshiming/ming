package ming.format.permit;

import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.validate.Validator;

public class UserFormat {

	private static String USER_ID_PATTERN = "[0-9a-zA-Z_.]{1,50}$";
	private static String USER_NAME_PATTERN = "[\\S]{1,60}";

	public static void validateUserId(String userId) {
		Validator.notBlank(userId, ErrorCodeSys.PARAM_NOT_NULL, "用户ID");
		Validator.notMatch(userId, USER_ID_PATTERN, ErrorCodeSys.PARAM_FORMAT_NOT_MATCH, "用户ID");
	}

	public static void validateName(String userName) {
		Validator.notBlank(userName, ErrorCodeSys.PARAM_NOT_NULL, "用户名称");
		Validator.notMatch(userName, USER_NAME_PATTERN, ErrorCodeSys.PARAM_NOT_NULL, "用户名称");

	}

	public static void validatePassword(String password, String passwordConfirm) {
		Validator.notBlank(password, ErrorCodeSys.PARAM_NOT_NULL, "新密码");
		Validator.notBlank(passwordConfirm, ErrorCodeSys.PARAM_NOT_NULL, "确认密码");
		Validator.isEqual(password, passwordConfirm, ErrorCodeSys.PARAM_NOT_EQUAL, "新密码","确认密码");

	}
}
