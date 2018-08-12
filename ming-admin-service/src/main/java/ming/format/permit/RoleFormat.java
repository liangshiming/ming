package ming.format.permit;

import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.validate.Validator;

public class RoleFormat {
	private static String ROLE_NAME_PATTERN = "[\\S]{1,60}";

	public static void validateRoleName(String roleName) {
		Validator.notBlank(roleName, ErrorCodeSys.PARAM_NOT_NULL, "角色名称");
		Validator.notMatch(roleName, ROLE_NAME_PATTERN, ErrorCodeSys.PARAM_FORMAT_NOT_MATCH, "角色名称");
	}
	public static void validateRoleId(String roleId) {
		Validator.notBlank(roleId, ErrorCodeSys.PARAM_NOT_NULL, "角色ID");
	}
}
