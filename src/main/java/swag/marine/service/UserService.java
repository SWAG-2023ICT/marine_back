package swag.marine.service;

import swag.marine.model.Store;
import swag.marine.model.User;

public interface UserService {
    Integer addUser(User user);
    Integer addStore(Store store);
    boolean idDuplicateCheck(String userId);
}
