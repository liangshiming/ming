package ming.admin.vo.trans;

import lombok.Data;
import ming.admin.vo.base.TransReq;

import java.util.List;
@Data
public class RolePermitReq extends TransReq {

	private String roleId;

	private List<String> permitList;
}
