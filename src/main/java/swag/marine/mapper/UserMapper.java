package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.User;

@Mapper
public interface UserMapper {
    Integer addUser(User user);
    User getUser(String userId);
    Integer updateUser(User user);
    Integer idDuplicateCheck(String userId);
    Integer isStore(String sellerId);
    Integer updatePassword(User user);
    User getLoginUser(String userId);
}
