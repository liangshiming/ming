package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryUserRoleListReq;
import ming.dto.query.QueryUserRoleListDto;
import ming.service.query.UserRoleListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryUserRoleListFacade extends BaseFacade {

	@Resource
	private UserRoleListService userRoleListService;

	public QueryRes queryUserRoleList(QueryUserRoleListReq userRoleListReq) {
		QueryUserRoleListDto queryRoleListDto = buildUserListDto(userRoleListReq);
		userRoleListService.queryUserRoleList(queryRoleListDto);
		return super.buildRes(userRoleListReq, queryRoleListDto.getUserRoleList());
	}

	private QueryUserRoleListDto buildUserListDto(QueryUserRoleListReq userRoleListReq) {
		QueryUserRoleListDto userRoleListDto = new QueryUserRoleListDto();
		userRoleListDto.setUserId(userRoleListReq.getUserId());
		return userRoleListDto;
	}
}
