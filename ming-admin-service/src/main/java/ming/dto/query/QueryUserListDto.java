package ming.dto.query;

import lombok.Data;
import ming.po.permit.User;

import java.util.List;

@Data
public class QueryUserListDto extends QueryBaseDto{
	private String userId;

	private String name;

	private String status;

	private List<User> userList;
}
