package ming.admin.vo.query;

import lombok.Data;
import ming.admin.vo.base.QueryReq;

@Data
public class QueryPermitListReq extends QueryReq {
	private String permitId;

	private String parentPermitId;

	private String name;

	private String status;

}
