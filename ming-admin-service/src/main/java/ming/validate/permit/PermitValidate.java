package ming.validate.permit;

import ming.constant.AdminErrorCode;
import ming.dao.permit.PermitMapper;
import ming.dao.permit.RoleMapper;
import ming.framework.validate.Validator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PermitValidate {

	@Resource
	private PermitMapper permitMapper;

	public void checkPermitIsNotExist(String permitId) {
		Validator.isNull(permitMapper.selectById(permitId), AdminErrorCode.PERMIT_EXIST);
	}

	public void checkPermitIsExist(String permitId) {
		Validator.notNull(permitMapper.selectById(permitId), AdminErrorCode.PERMIT_NOT_EXIST);
	}
}
