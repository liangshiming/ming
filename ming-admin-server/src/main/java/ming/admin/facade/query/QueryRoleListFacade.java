package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryRoleListReq;
import ming.dto.query.QueryRoleListDto;
import ming.service.query.RoleListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryRoleListFacade extends BaseFacade {

	@Resource
	private RoleListService roleListService;

	public QueryRes queryAllRole(QueryRoleListReq queryUserListReq){
		QueryRoleListDto queryRoleListDto = buildUserListDto(queryUserListReq);
		roleListService.queryAllRoleList(queryRoleListDto);
		return super.buildRes(queryUserListReq,queryRoleListDto.getRoleList());
	}

	private QueryRoleListDto buildUserListDto(QueryRoleListReq queryRoleListReq) {
		QueryRoleListDto queryRoleListDto = new QueryRoleListDto();
		queryRoleListDto.setName(queryRoleListReq.getName());
		queryRoleListDto.setPageSize(queryRoleListReq.getPageSize());
		queryRoleListDto.setCurrentPage(queryRoleListReq.getCurrentPage());
		return queryRoleListDto;
	}
}
