package ming.admin.vo.trans;

import lombok.Data;
import ming.admin.vo.base.TransReq;

@Data
public class PermitReq extends TransReq {
	private String permitId;

	private String parentPermitId;

	private String name;

	private String icon;

	private String url;

	private String status;
}
