package ming.framework.core.service;

import lombok.extern.slf4j.Slf4j;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.core.po.ErrorCode;
import ming.framework.core.service.cache.CacheService;
import ming.framework.exception.SystemException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jrick on 2018/8/6.
 */
@Slf4j
@Service
public class ErrCodeService {

	@Resource
	private CacheService cacheService;

	public String formatException(Exception ex) {
		Throwable cause = ex.getCause() == null ? ex : ex.getCause();
		String message;
		if (cause != null && cause instanceof SystemException) {
			message = this.formatSystemException((SystemException) cause);
		} else {
			message = this.formatOtherException(ex);
		}

		return message;
	}

	private String formatSystemException(SystemException ex) {
		String message = this.formatMessage(ex.getCode(), ex.getParamArr());
		log.info("[系统异常]==>:" + message, ex);
		return message;
	}

	private String formatOtherException(Exception ex) {
		log.error("[其他异常]==>:" + ex.getClass(), ex);
		String message = this.formatMessage(ErrorCodeSys.SYS_ERR.toString());
		return message;
	}

	public String formatMessage(String errCode, String... paramArr) {
		ErrorCode errorCodeConfig = cacheService.getErrorCode(errCode);
		if (errorCodeConfig == null) {
			log.error("对应ERROR_CODE不存在   " + errCode);
			return errCode;
		} else {
			String template = errorCodeConfig.getMessage();
			return paramArr != null && paramArr.length > 0 ? String.format(template, paramArr) : template;
		}
	}
}
