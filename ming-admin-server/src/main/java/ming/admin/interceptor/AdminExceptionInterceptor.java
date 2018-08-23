package ming.admin.interceptor;

import ming.admin.vo.base.TransRes;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.exception.BaseExceptionInterceptor;
import ming.framework.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class AdminExceptionInterceptor extends BaseExceptionInterceptor<TransRes> {


	@Override
	protected TransRes buildResForException(Exception ex) {
		TransRes res = new TransRes();
		res.setResultCode(ErrorCodeSys.SYS_ERR.toString());
		res.setMessage(formatMessage(ex));
		res.setTimestamp(new Date());
		return res;
	}

	@Override
	protected TransRes buildResForSystemException(SystemException ex) {
		TransRes res = new TransRes();
		res.setResultCode(ex.getCode());
		res.setMessage(formatMessage(ex));
		res.setTimestamp(new Date());
		return res;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleException(HttpServletRequest request, Exception ex) {
		return super.handleException(request, ex);
	}
}
