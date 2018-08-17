package ming.dao.query;

import ming.po.permit.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryUserMapper {

	List<User> selectAllUserList(@Param("userId") String userId, @Param("name") String name,
			@Param("status") String status);
}
