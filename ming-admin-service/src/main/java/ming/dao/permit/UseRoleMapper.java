package ming.dao.permit;

import ming.po.permit.UseRole;
import org.apache.ibatis.annotations.Param;

public interface UseRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int insert(UseRole record);

    int insertSelective(UseRole record);
}
