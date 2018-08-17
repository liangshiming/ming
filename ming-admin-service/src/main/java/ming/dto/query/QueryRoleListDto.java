package ming.dto.query;

import lombok.Data;
import ming.po.permit.Role;

import java.util.List;

@Data
public class QueryRoleListDto extends QueryBaseDto{

	private String name;

	private List<Role> roleList;
}
