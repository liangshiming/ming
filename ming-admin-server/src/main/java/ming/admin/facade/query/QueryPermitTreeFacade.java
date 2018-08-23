package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryPermitTreeReq;
import ming.dto.query.QueryPermitTreeDto;
import ming.service.query.PermitTreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryPermitTreeFacade extends BaseFacade {

	@Resource
	private PermitTreeService permitTreeService;

	public QueryRes queryPermitTree(QueryPermitTreeReq queryPermitTreeReq) {
		QueryPermitTreeDto queryPermitListDto = buildQueryPermitListDto(queryPermitTreeReq);
		permitTreeService.queryPermitListByUserId(queryPermitListDto);
		return super.buildRes(queryPermitTreeReq, queryPermitListDto.getPermitTreeList());
	}

	public QueryRes queryAllPermitTree(QueryPermitTreeReq queryPermitTreeReq) {
		QueryPermitTreeDto queryPermitListDto = buildQueryPermitListDto(queryPermitTreeReq);
		permitTreeService.queryAllPermitList(queryPermitListDto);
		return super.buildRes(queryPermitTreeReq, queryPermitListDto.getPermitTreeList());
	}

	private QueryPermitTreeDto buildQueryPermitListDto(QueryPermitTreeReq queryPermitTreeReq) {
		QueryPermitTreeDto queryPermitListDto = new QueryPermitTreeDto();
		queryPermitListDto.setUserId(queryPermitTreeReq.getOperator());
		queryPermitListDto.setLoginToken(queryPermitTreeReq.getLoginToken());
		return queryPermitListDto;
	}
}
