package ming.dao.query;

import ming.po.permit.Permit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryPermitMapper {

    List<Permit> selectPermitListByRoleId(String permitId);

    List<Permit> selectPermitListByUserId(String userId);

    List<Permit> selectAllPermitList(@Param("permitId")String permitId,@Param("permitParentId")String permitParentId,@Param("name")String name,@Param("status")String status);
}
