package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryUserListReq;
import ming.dto.query.QueryUserListDto;
import ming.service.query.UserListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryUserListFacade extends BaseFacade {

	@Resource
	private UserListService userListService;

	public QueryRes queryAllUser(QueryUserListReq queryUserListReq){
		QueryUserListDto queryUserListDto = buildUserListDto(queryUserListReq);
		userListService.queryAllUserList(queryUserListDto);
		return super.buildRes(queryUserListReq,queryUserListDto.getUserList());
	}

	private QueryUserListDto buildUserListDto(QueryUserListReq queryUserListReq) {
		QueryUserListDto queryUserListDto = new QueryUserListDto();
		queryUserListDto.setUserId(queryUserListReq.getUserId());
		queryUserListDto.setName(queryUserListReq.getName());
		queryUserListDto.setStatus(queryUserListReq.getStatus());
		queryUserListDto.setPageSize(queryUserListReq.getPageSize());
		queryUserListDto.setCurrentPage(queryUserListReq.getCurrentPage());
		return queryUserListDto;
	}
}
