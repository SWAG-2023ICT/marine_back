package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Store;
import swag.marine.model.User;

@Mapper
public interface UserMapper {
    Integer addUser(User user);
    Integer addStore(Store store);
    Integer idDuplicateCheck(String userId);
}
