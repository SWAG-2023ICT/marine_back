package swag.marine.service;

import swag.marine.model.User;

public interface UserService {
    Integer addUser(User user);
    boolean idDuplicateCheck(String userId);
}
