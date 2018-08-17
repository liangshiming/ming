package ming.dto.query;

import lombok.Data;
import ming.po.permit.UserRole;

import java.util.List;

@Data
public class QueryUserRoleListDto extends QueryBaseDto{

	private String userId;

	private List<UserRole> userRoleList;
}
