package ming.format.permit;

import ming.constant.AdminErrorCode;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.validate.Validator;

public class PermitFormat {
	private static String PERMIT_ID_PATTERN = "[0-9a-zA-Z_.]{1,50}$";

	public static void validatePermitId(String permitId) {
		Validator.notBlank(permitId, ErrorCodeSys.PARAM_NOT_NULL, "角色名称");
		Validator.notMatch(permitId, PERMIT_ID_PATTERN, AdminErrorCode.PERMIT_ID_NOT_MATCH);
	}
}
