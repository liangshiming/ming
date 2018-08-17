package ming.admin.vo.base;

import lombok.Data;

import java.util.Date;

@Data
public class QueryRes <T> extends QueryReq{
	private String resultCode;
	private String message;
	private Date timestamp;
	private String totalSize;
	private T body;
}
