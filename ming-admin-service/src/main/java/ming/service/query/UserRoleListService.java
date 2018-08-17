package ming.service.query;

import ming.dao.query.QueryUserRoleMapper;
import ming.dto.query.QueryUserRoleListDto;
import ming.po.permit.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleListService {

	@Resource
	private QueryUserRoleMapper queryUserRoleMapper;

	public void queryUserRoleList(QueryUserRoleListDto userRoleListDto) {
		List<UserRole> userRoleList = queryUserRoleMapper.selectRoleListByUserId(userRoleListDto.getUserId());
		userRoleListDto.setUserRoleList(userRoleList);
	}
}
