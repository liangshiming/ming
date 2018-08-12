package ming.dto.permit;

import lombok.Data;

@Data
public class LoginDto {
	private String userId;

	private String nickName;

	private String password;

	private String verifyCode;

	private String token;
}
