package ming.dto.query;

import lombok.Data;

import java.util.List;

@Data
public class QueryPermitTreeDto {
	private String userId;
	private List<PermitNode> permitTreeList;
}
