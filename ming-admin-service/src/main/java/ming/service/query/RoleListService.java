package ming.service.query;

import ming.dao.query.QueryRoleMapper;
import ming.dto.query.QueryRoleListDto;
import ming.framework.util.PageUtil;
import ming.po.permit.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleListService {

	@Resource
	private QueryRoleMapper queryRoleMapper;

	public void queryAllRoleList(QueryRoleListDto queryRoleListDto) {
		PageUtil.setPaging(queryRoleListDto.getCurrentPage(), queryRoleListDto.getPageSize());
		List<Role> roleList = queryRoleMapper.selectAllRoleList(queryRoleListDto.getName());
		queryRoleListDto.setRoleList(roleList);
	}
}
