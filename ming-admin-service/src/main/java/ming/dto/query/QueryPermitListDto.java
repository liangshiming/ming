package ming.dto.query;

import lombok.Data;
import ming.po.permit.Permit;

import java.util.List;

@Data
public class QueryPermitListDto {
	private String userId;

	private String roleId;

	private List<Permit> permitList;
}
