package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.base.TransRes;
import ming.admin.vo.query.QueryRolePermitListReq;
import ming.dto.query.QueryPermitListDto;
import ming.service.query.PermitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryPermitListFacade extends BaseFacade {

	@Resource
	private PermitListService permitListService;

	public QueryRes queryRolePermitList(QueryRolePermitListReq rolePermitListReq) {
		QueryPermitListDto queryPermitListDto = buildQueryPermitListDto(rolePermitListReq);
		permitListService.queryPermitListByRoleId(queryPermitListDto);
		return super.buildRes(rolePermitListReq, queryPermitListDto.getPermitList());
	}

	private QueryPermitListDto buildQueryPermitListDto(QueryRolePermitListReq rolePermitListReq) {
		QueryPermitListDto queryPermitListDto = new QueryPermitListDto();
		queryPermitListDto.setRoleId(rolePermitListReq.getRoleId());
		return queryPermitListDto;
	}
}
