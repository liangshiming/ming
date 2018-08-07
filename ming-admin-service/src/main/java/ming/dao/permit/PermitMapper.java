package ming.dao.permit;

import ming.po.permit.Permit;

public interface PermitMapper {
    int delete(String permitId);

    int insert(Permit record);

    int update(Permit record);

    Permit selectById(String permitId);
}
