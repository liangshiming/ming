package ming.admin.vo.query;

import lombok.Data;
import ming.admin.vo.base.QueryReq;

@Data
public class QueryUserListReq extends QueryReq {

	private String userId;

	private String name;

	private String status;
}
