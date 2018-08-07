package ming.po.permit;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
	private String roleId;

	private String name;

	private Date createTime;

	private Date lastUpdateTime;
}
