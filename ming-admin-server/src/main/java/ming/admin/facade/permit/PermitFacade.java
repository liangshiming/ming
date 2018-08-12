package ming.admin.facade.permit;

import ming.admin.facade.BaseFacade;
import ming.admin.vo.base.TransRes;
import ming.admin.vo.trans.PermitReq;
import ming.dto.permit.PermitDto;
import ming.service.permit.PermitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermitFacade extends BaseFacade {

	@Resource
	private PermitService permitService;

	public TransRes permitAdd(PermitReq permitReq){
		PermitDto permitDto = buildPermitDto(permitReq);
		permitService.add(permitDto);
		return buildRes(permitReq);
	}

	public TransRes permitDelete(PermitReq permitReq){
		PermitDto permitDto = buildPermitDto(permitReq);
		permitService.delete(permitDto);
		return buildRes(permitReq);
	}

	public TransRes permitModify(PermitReq permitReq){
		PermitDto permitDto = buildPermitDto(permitReq);
		permitService.modify(permitDto);
		return buildRes(permitReq);
	}

	private PermitDto buildPermitDto(PermitReq permitReq) {
		PermitDto permitDto = new PermitDto();
		permitDto.setPermitId(permitReq.getPermitId());
		permitDto.setParentPermitId(permitReq.getParentPermitId());
		permitDto.setName(permitReq.getName());
		permitDto.setIcon(permitReq.getIcon());
		permitDto.setUrl(permitReq.getUrl());
		permitDto.setStatus(permitReq.getStatus());
		return permitDto;
	}

}
