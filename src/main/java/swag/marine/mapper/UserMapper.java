package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.User;

@Mapper
public interface UserMapper {
    Integer addUser(User user);
    Integer idDuplicateCheck(String userId);
}
