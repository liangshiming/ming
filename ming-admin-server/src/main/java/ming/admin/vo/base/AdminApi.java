package ming.admin.vo.base;

import lombok.Data;
import ming.framework.core.vo.BaseApi;

@Data
public class AdminApi extends BaseApi {
	private String userId;

	private String nickName;

	private String loginToken;
}
