package ming.dto.permit;

import lombok.Data;

@Data
public class PermitDto {
	private String permitId;

	private String parentPermitId;

	private String name;

	private String icon;

	private String url;

	private String status;
}
