package ming.dto.query;

import lombok.Data;
import ming.po.permit.Permit;

import java.util.List;

@Data
public class QueryPermitListDto extends QueryBaseDto{
	private String permitId;

	private String parentPermitId;

	private String name;

	private String status;

	private List<Permit> permitList;
}
