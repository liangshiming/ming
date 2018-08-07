package ming.po.permit;

import lombok.Data;

import java.util.Date;

@Data
public class Permit {
	private String permitId;

	private String parentPermitId;

	private String name;

	private String icon;

	private String url;

	private String status;

	private Date createTime;

	private Date lastUpdateTime;
}
