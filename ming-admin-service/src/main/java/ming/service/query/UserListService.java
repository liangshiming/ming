package ming.service.query;

import ming.dao.query.QueryUserMapper;
import ming.dto.query.QueryUserListDto;
import ming.framework.util.PageUtil;
import ming.po.permit.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserListService {

	@Resource
	private QueryUserMapper queryUserMapper;

	public void queryAllUserList(QueryUserListDto queryUserListDto) {
		PageUtil.setPaging(queryUserListDto.getCurrentPage(), queryUserListDto.getPageSize());
		List<User> userList = queryUserMapper
				.selectAllUserList(queryUserListDto.getUserId(), queryUserListDto.getName(),
						queryUserListDto.getStatus());
		queryUserListDto.setUserList(userList);
	}

}
