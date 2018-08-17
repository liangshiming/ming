package ming.validate.permit;

import ming.constant.AdminErrorCode;
import ming.dao.permit.PermitMapper;
import ming.dao.permit.RoleMapper;
import ming.framework.constant.errcode.ErrorCodeSys;
import ming.framework.validate.Validator;
import org.apache.commons.lang3.StringUtils;
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

	public void checkParentPermitIdIsExist(String parentPermitId) {
		if (StringUtils.isNotBlank(parentPermitId)) {
			Validator.notNull(permitMapper.selectById(parentPermitId), AdminErrorCode.PARENT_PERMIT_NOT_EXIST);
		}
	}
}
