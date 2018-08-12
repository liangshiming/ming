package ming.validate.permit;

import ming.constant.AdminErrorCode;
import ming.dao.permit.UserMapper;
import ming.dao.permit.UserRoleMapper;
import ming.framework.validate.Validator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserValidate {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	public void checkUserIsNotExist(String userId) {
		Validator.isNull(userMapper.selectById(userId), AdminErrorCode.USER_EXIST);
	}

	public void checkUserIsExist(String userId) {
		Validator.notNull(userMapper.selectById(userId), AdminErrorCode.USER_NOT_EXIST);
	}
}
