package ming.framework.core.po;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorCode {
	private String errCode;
	private String message;
	private String author;
	private Date createTime;
	private Date lastUpdateTime;
}
