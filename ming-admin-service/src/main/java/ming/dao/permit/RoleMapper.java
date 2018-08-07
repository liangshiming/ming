package ming.dao.permit;

import ming.po.permit.Role;

public interface RoleMapper {
    int delete(String roleId);

    int insert(Role record);

    int update(Role record);

    Role selectById(String roleId);
}
