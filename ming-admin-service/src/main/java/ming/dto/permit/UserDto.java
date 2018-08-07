package ming.dto.permit;

import lombok.Data;

@Data
public class UserDto {
	private String userId;

	private String name;

	private String nickName;

	private String password;

	private String passwordConfirm;

	private String status;

}
