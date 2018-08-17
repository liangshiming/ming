package ming.service.query;

import ming.dao.query.QueryPermitMapper;
import ming.dto.permit.PermitDto;
import ming.dto.query.QueryPermitListDto;
import ming.framework.util.PageUtil;
import ming.framework.util.TimeProvider;
import ming.po.permit.Permit;
import ming.validate.permit.UserValidate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.List;

@Service
public class PermitListService {

	@Resource
	private QueryPermitMapper queryPermitMapper;

	public void queryPermitList(QueryPermitListDto permitListDto) {
		PageUtil.setPaging(permitListDto.getCurrentPage(), permitListDto.getPageSize());
		List<Permit> permitList = queryPermitMapper
				.selectAllPermitList(permitListDto.getPermitId(), permitListDto.getParentPermitId(),
						permitListDto.getName(), permitListDto.getStatus());
		permitListDto.setPermitList(permitList);
	}

}
