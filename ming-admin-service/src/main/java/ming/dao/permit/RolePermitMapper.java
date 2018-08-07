package ming.dao.permit;

import ming.po.permit.RolePermit;
import org.apache.ibatis.annotations.Param;

public interface RolePermitMapper {
    int delete(@Param("roleId") String roleId, @Param("permitId") String permitId);

    int insert(RolePermit record);

}
