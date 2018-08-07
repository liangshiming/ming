package ming.po.permit;

import lombok.Data;

import java.util.Date;

@Data
public class User {
	private String userId;

	private String name;

	private String nickName;

	private String password;

	private String status;

	private Date createTime;

	private Date lastUpdateTime;
}
