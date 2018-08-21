package ming.validate.permit;

import ming.constant.AdminErrorCode;
import ming.constant.TokenItem;
import ming.framework.validate.Validator;
import ming.token.TokenService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginValidate {

	@Resource
	private TokenService tokenService;

	public void checkVerifyCodeIsEqual(String token, String verifyCode) {
		String verifyCodeCache = tokenService.getItem(token, TokenItem.GRAPH_CODE);
		tokenService.removeItem(token, TokenItem.GRAPH_CODE);
		Validator.isEqual(verifyCode, verifyCodeCache, AdminErrorCode.VERIFY_CODE_ERROR);
	}

}
