package ming.service.permit;

import ming.dao.permit.UserMapper;
import ming.dao.permit.UserRoleMapper;
import ming.dto.permit.UserDto;
import ming.dto.permit.UserRoleDto;
import ming.format.permit.RoleFormat;
import ming.format.permit.UserFormat;
import ming.framework.util.CollectionUtil;
import ming.framework.util.EncryptUtil;
import ming.framework.util.TimeProvider;
import ming.po.permit.User;
import ming.po.permit.UserRole;
import ming.validate.permit.RoleValidate;
import ming.validate.permit.UserValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

	@Resource
	private TimeProvider timeProvider;
	@Resource
	private UserValidate userValidate;
	@Resource
	private RoleValidate roleValidate;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	@Transient
	public void add(UserDto userDto) {
		validateForAdd(userDto);
		User user = buildUserForAdd(userDto);
		userMapper.insert(user);
	}

	@Transient
	public void delete(UserDto userDto) {
		validateForDelete(userDto);
		userRoleMapper.deleteByUserId(userDto.getUserId());
		userMapper.delete(userDto.getUserId());
	}

	@Transient
	public void modify(UserDto userDto) {
		validateForModify(userDto);
		User user = buildUserForModify(userDto);
		userMapper.update(user);
	}

	@Transient
	public void modifyRoleList(UserRoleDto userRoleDto) {
		validateForModifyRoleList(userRoleDto);
		updateRoleList(userRoleDto);
	}

	private void validateForAdd(UserDto userDto) {
		UserFormat.validateUserId(userDto.getUserId());
		UserFormat.validateName(userDto.getName());
		UserFormat.validatePassword(userDto.getPassword(), userDto.getPasswordConfirm());
		userValidate.checkUserIsNotExist(userDto.getUserId());
	}

	private User buildUserForAdd(UserDto userDto) {
		Date currentTime = timeProvider.getCurrentTime();
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setNickName(userDto.getNickName());
		user.setPassword(EncryptUtil.bcryptEncode(userDto.getPassword()));
		user.setStatus(userDto.getStatus());
		user.setCreateTime(currentTime);
		user.setLastUpdateTime(currentTime);
		return user;
	}

	private void validateForDelete(UserDto userDto) {
		userValidate.checkUserIsNotExist(userDto.getUserId());
	}

	private void validateForModify(UserDto userDto) {
		UserFormat.validateName(userDto.getName());
		if (StringUtils.isNotBlank(userDto.getPassword())) {
			UserFormat.validatePassword(userDto.getPassword(), userDto.getPasswordConfirm());
		}
		userValidate.checkUserIsExist(userDto.getUserId());
	}

	private User buildUserForModify(UserDto userDto) {
		User user = userMapper.selectById(userDto.getUserId());
		Date currentTime = timeProvider.getCurrentTime();
		user.setName(userDto.getName());
		user.setNickName(userDto.getNickName());
		if (StringUtils.isNotBlank(userDto.getPassword())) {
			user.setPassword(EncryptUtil.bcryptEncode(userDto.getPassword()));
		}
		user.setStatus(userDto.getStatus());
		user.setLastUpdateTime(currentTime);
		return user;
	}

	private void validateForModifyRoleList(UserRoleDto userRoleDto) {
		UserFormat.validateUserId(userRoleDto.getUserId());
		userValidate.checkUserIsExist(userRoleDto.getUserId());
		validateRoleListIsExist(userRoleDto.getRoleList());
	}

	private void validateRoleListIsExist(List<String> roleList) {
		if (CollectionUtil.isNotEmpty(roleList)) {
			for (String roleId : roleList) {
				RoleFormat.validateRoleId(roleId);
				roleValidate.checkRoleIsExist(roleId);
			}
		}
	}

	private void updateRoleList(UserRoleDto userRoleDto) {
		userRoleMapper.deleteByUserId(userRoleDto.getUserId());
		if(CollectionUtil.isNotEmpty(userRoleDto.getRoleList())) {
			List<UserRole> userRoleList = buildUserRoleList(userRoleDto);
			userRoleMapper.batchInsert(userRoleList);
		}
	}

	private List<UserRole> buildUserRoleList(UserRoleDto userRoleDto) {
		String userId = userRoleDto.getUserId();
		List<String> roleList = userRoleDto.getRoleList();
		List<UserRole> userRoleList = new ArrayList<>();
		for(String roleId : roleList){
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			userRoleList.add(userRole);
		}
		return userRoleList;
	}
}
