package ming.framework.exception;

import lombok.extern.slf4j.Slf4j;
import ming.framework.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jrick on 2018/1/11.
 */
@Slf4j
public abstract class BaseExceptionInterceptor<T>{


	protected abstract T buildResForException(Exception ex);

	protected abstract T buildResForRuleException(SystemException cause);

	protected String handleException(HttpServletRequest request, Exception ex) {
		Throwable cause = ex.getCause() == null ? ex : ex.getCause();
		Object res;
		if (cause != null && cause instanceof SystemException) {
			res = this.buildResForRuleException((SystemException)cause);
		} else {
			res = this.buildResForException(ex);
		}
		return JsonUtil.toJson(res);
	}
}
