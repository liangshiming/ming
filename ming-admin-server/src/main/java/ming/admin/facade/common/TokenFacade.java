package ming.admin.facade.common;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.TransRes;
import ming.admin.vo.common.TokenReq;
import ming.token.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TokenFacade extends BaseFacade {

	@Resource
	private TokenService tokenService;

	public TransRes getToken(TokenReq tokenReq) {
		String token = tokenService.createUnLoginToken();
		tokenReq.setToken(token);
		return super.buildRes(tokenReq);
	}

}
