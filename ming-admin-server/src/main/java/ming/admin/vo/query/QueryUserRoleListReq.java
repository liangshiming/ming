package ming.admin.vo.query;

import lombok.Data;
import ming.admin.vo.base.QueryReq;

@Data
public class QueryUserRoleListReq extends QueryReq {
	private String userId;

}
