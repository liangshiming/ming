package ming.admin.facade.query;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.QueryRes;
import ming.admin.vo.query.QueryPermitListReq;
import ming.dto.query.QueryPermitListDto;
import ming.service.query.PermitListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QueryPermitListFacade extends BaseFacade {

	@Resource
	private PermitListService permitListService;

	public QueryRes queryAllPermitList(QueryPermitListReq permitListReq) {
		QueryPermitListDto permitListDto = buildQueryPermitListDto(permitListReq);
		permitListService.queryPermitList(permitListDto);
		return super.buildRes(permitListReq, permitListDto.getPermitList());
	}

	private QueryPermitListDto buildQueryPermitListDto(QueryPermitListReq permitListReq) {
		QueryPermitListDto permitListDto = new QueryPermitListDto();
		permitListDto.setPermitId(permitListReq.getPermitId());
		permitListDto.setParentPermitId(permitListReq.getParentPermitId());
		permitListDto.setName(permitListReq.getName());
		permitListDto.setStatus(permitListReq.getStatus());
		permitListDto.setPageSize(permitListReq.getPageSize());
		permitListDto.setCurrentPage(permitListReq.getCurrentPage());
		return permitListDto;
	}
}
