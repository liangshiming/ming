package ming.framework.exception;

import lombok.Data;
import ming.framework.constant.errcode.ErrorCode;

/**
 * Created by Jrick on 2018/1/11.
 */
@Data
public class SystemException extends RuntimeException {

	private String code;
	private String[] paramArr;

	public SystemException(ErrorCode errorCode, Throwable ex, String... paramArr) {
		super(errorCode + "[" + ex.getCause() + "]", ex);
		this.code = errorCode.toString();
		this.paramArr = paramArr;
	}

	public SystemException(ErrorCode errorCode, String... paramArr) {
		super(errorCode.toString());
		this.code = errorCode.toString();
		this.paramArr = paramArr;
	}
}
