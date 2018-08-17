package ming.dao.query;

import ming.po.permit.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryRoleMapper {

	List<Role> selectAllRoleList(@Param("name") String name);

}
