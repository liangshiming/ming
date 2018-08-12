package ming.admin.vo.trans;

import lombok.Data;
import ming.admin.vo.base.TransReq;
@Data
public class UserReq extends TransReq {

	private String userId;

	private String name;

	private String nickName;

	private String password;

	private String passwordConfirm;

	private String status;
}
