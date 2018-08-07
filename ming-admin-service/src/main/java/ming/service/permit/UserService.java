package ming.service.permit;

import ming.dao.permit.UserMapper;
import ming.dto.permit.UserDto;
import ming.format.permit.UserFormat;
import ming.framework.util.TimeProvider;
import ming.po.permit.User;
import ming.validate.permit.UserValidate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.Date;

@Service
public class UserService {

	@Resource
	private TimeProvider timeProvider;
	@Resource
	private UserValidate userValidate;
	@Resource
	private UserMapper userMapper;

	@Transient
	public void add(UserDto userDto) {
		validateForAdd(userDto);
		User user = buildUserForAdd(userDto);
		userMapper.insert(user);
	}

	private void validateForAdd(UserDto userDto) {
		UserFormat.validateUserId(userDto.getUserId());
		UserFormat.validateName(userDto.getName());
	}

	private User buildUserForAdd(UserDto userDto) {
		Date currentTime = timeProvider.getCurrentTime();
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setNickName(userDto.getNickName());
		user.setPassword(userDto.getPassword());
		user.setStatus(userDto.getStatus());
		user.setCreateTime(currentTime);
		user.setLastUpdateTime(currentTime);
		return user;
	}
}
