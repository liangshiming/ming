package ming.service.query;

import ming.dao.query.QueryRolePermitMapper;
import ming.dto.query.QueryRolePermitListDto;
import ming.po.permit.RolePermit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolePermitListService {

	@Resource
	private QueryRolePermitMapper queryRolePermitMapper;

	public void queryRolePermitList(QueryRolePermitListDto rolePermitListDto) {
		List<RolePermit> rolePermitList = queryRolePermitMapper.selectRolePermitListByRoleId(rolePermitListDto.getRoleId());
		rolePermitListDto.setRolePermitList(rolePermitList);
	}
}
