package ming.dto.query;

import lombok.Data;

@Data
public class QueryBaseDto {
	private String currentPage;
	private String pageSize;
	private String loginToken;
}
