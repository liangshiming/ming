package ming.service.query;

import ming.dao.query.QueryPermitMapper;
import ming.dto.permit.PermitDto;
import ming.dto.query.QueryPermitListDto;
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

	public void queryPermitListByRoleId(QueryPermitListDto queryPermitListDto) {
		List<Permit> permitList = queryPermitMapper.selectPermitListByRoleId(queryPermitListDto.getRoleId());
		queryPermitListDto.setPermitList(permitList);
	}

}
