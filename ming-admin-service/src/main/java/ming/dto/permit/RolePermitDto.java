package ming.dto.permit;

import lombok.Data;

import java.util.List;

@Data
public class RolePermitDto {
	private String roleId;

	private List<String> permitList;
}
