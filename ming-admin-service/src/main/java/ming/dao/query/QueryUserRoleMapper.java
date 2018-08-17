package ming.dao.query;

import ming.po.permit.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryUserRoleMapper {

	List<UserRole> selectRoleListByUserId(@Param("userId") String userId);
}
