package ming.admin.vo.trans;

import lombok.Data;
import ming.admin.vo.base.TransReq;

import java.util.List;

@Data
public class UserRoleReq extends TransReq {

	private String userId;

	private List<String> roleList;
}
