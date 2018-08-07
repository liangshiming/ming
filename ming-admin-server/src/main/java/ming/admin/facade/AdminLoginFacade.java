package ming.admin.facade;

import ming.admin.vo.base.TransReq;
import ming.admin.vo.base.TransRes;
import ming.dto.permit.login.LoginDto;
import ming.service.permit.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminLoginFacade {

	@Resource
	private LoginService loginService;

	public TransRes login(TransReq transReq) {
		loginService.login(buildLoginDto(transReq));
		return null;
	}

	private LoginDto buildLoginDto(TransReq transReq) {
		LoginDto loginDto = new LoginDto();
		return loginDto;
	}
}
