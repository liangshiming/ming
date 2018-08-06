package ming.service.login;

import lombok.extern.slf4j.Slf4j;
import ming.dto.login.LoginDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

	public void login(LoginDto loginDto){
		log.debug("登录成功:{}",loginDto);
	}
}