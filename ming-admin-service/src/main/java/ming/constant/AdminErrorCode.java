package ming.constant;

import ming.framework.constant.errcode.ErrorCode;

public enum  AdminErrorCode implements ErrorCode {
	PASSWORD_LOGIN_ERROR,
	USER_NOT_EXIST,
	USER_EXIST,
	ROLE_NOT_EXIST,
	ROLE_EXIST,
	PERMIT_NOT_EXIST,
	PERMIT_EXIST,
	PERMIT_ID_NOT_MATCH
}
