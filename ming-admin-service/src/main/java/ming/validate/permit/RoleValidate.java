package ming.validate.permit;

import ming.constant.AdminErrorCode;
import ming.dao.permit.RoleMapper;
import ming.dao.permit.UserMapper;
import ming.framework.validate.Validator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RoleValidate {

	@Resource
	private RoleMapper roleMapper;

	public void checkRoleIsNotExist(String roleName) {
		Validator.isNull(roleMapper.selectByRoleName(roleName), AdminErrorCode.ROLE_EXIST);
	}

	public void checkRoleIsExist(String roleId) {
		Validator.notNull(roleMapper.selectById(roleId), AdminErrorCode.ROLE_NOT_EXIST);
	}
}
