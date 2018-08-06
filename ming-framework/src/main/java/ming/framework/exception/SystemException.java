package ming.framework.exception;

import lombok.Data;
import ming.framework.constant.errcode.ErrorCode;

/**
 * Created by Jrick on 2018/1/11.
 */
@Data
public class SystemException extends RuntimeException {

	private String code;

	public SystemException(ErrorCode errorCode, Throwable ex) {
		super(errorCode + "[" + ex.getCause() + "]", ex);
		this.code = errorCode.toString();
	}

	public SystemException(ErrorCode errorCode) {
		super(errorCode.toString());
		this.code = errorCode.toString();
	}
}
