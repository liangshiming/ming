package ming.dto.permit;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleDto {
	private String userId;

	private List<String> roleList;
}
