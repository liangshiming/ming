package ming.dto.query;

import lombok.Data;
import ming.po.permit.RolePermit;

import java.util.List;

@Data
public class QueryRolePermitListDto extends QueryBaseDto{

	private String roleId;

	private List<RolePermit> rolePermitList;
}
