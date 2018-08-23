package ming.dao.permit;

import ming.po.permit.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
	int deleteByUserId(@Param("userId") String userId);

	int insert(UserRole record);

	int batchInsert(List<UserRole> userRoleList);

	int deleteByRoleId(@Param("roleId") String roleId);
}
