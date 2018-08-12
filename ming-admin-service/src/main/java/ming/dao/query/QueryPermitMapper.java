package ming.dao.query;

import ming.po.permit.Permit;

import java.util.List;

public interface QueryPermitMapper {

    List<Permit> selectPermitListByRoleId(String permitId);

    List<Permit> selectPermitListByUserId(String userId);
}
