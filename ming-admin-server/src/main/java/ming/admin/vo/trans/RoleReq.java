package ming.admin.vo.trans;

import lombok.Data;
import ming.admin.vo.base.TransReq;
@Data
public class RoleReq extends TransReq {

	private String roleId;
	private String name;
}
