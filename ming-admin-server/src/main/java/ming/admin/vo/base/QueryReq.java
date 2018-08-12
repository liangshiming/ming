package ming.admin.vo.base;

import lombok.Data;

@Data
public class QueryReq extends AdminApi {
	private String currentPage;
	private String pageSize;
}
