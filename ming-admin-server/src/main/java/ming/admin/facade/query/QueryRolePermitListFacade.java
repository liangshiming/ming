package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryRolePermitListReq;
import ming.dto.query.QueryRolePermitListDto;
import ming.service.query.RolePermitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryRolePermitListFacade extends BaseFacade {

	@Resource
	private RolePermitListService rolePermitListService;

	public QueryRes queryRolePermitList(QueryRolePermitListReq rolePermitListReq) {
		QueryRolePermitListDto rolePermitListDto = buildRolePermitListDto(rolePermitListReq);
		rolePermitListService.queryRolePermitList(rolePermitListDto);
		return super.buildRes(rolePermitListReq, rolePermitListDto.getRolePermitList());
	}

	private QueryRolePermitListDto buildRolePermitListDto(QueryRolePermitListReq rolePermitListReq) {
		QueryRolePermitListDto rolePermitListDto = new QueryRolePermitListDto();
		rolePermitListDto.setRoleId(rolePermitListReq.getRoleId());
		return rolePermitListDto;
	}
}
