package ming.admin.vo.base;

import lombok.Data;

import java.util.Date;

@Data
public class TransRes <T> extends TransReq{
	private String resultCode;
	private String message;
	private Date timestamp;

	private T body;
}
