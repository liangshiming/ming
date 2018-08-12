package ming.dao.permit;

import ming.po.permit.RolePermit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermitMapper {

    int insert(RolePermit record);

	int batchInsert(List<RolePermit> rolePermitList);

	int deleteByRoleId(@Param("roleId")String roleId);

	List<String> selectByRoleId(@Param("roleId")String roleId);
}
