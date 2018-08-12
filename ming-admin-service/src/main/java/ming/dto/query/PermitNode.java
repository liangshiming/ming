package ming.dto.query;

import lombok.Data;
import ming.dto.permit.PermitDto;

import java.util.List;

@Data
public class PermitNode extends PermitDto {

	private List<PermitNode> permitTreeList;
}
