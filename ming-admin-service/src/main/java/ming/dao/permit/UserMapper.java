package ming.dao.permit;

import ming.po.permit.User;

public interface UserMapper {
    int delete(String userId);

    int insert(User record);

    User selectById(String userId);

    int update(User record);
}
