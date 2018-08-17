package ming.dao.query;

import ming.po.permit.RolePermit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryRolePermitMapper {

	List<RolePermit> selectRolePermitListByRoleId(@Param("roleId") String roleId);
}
