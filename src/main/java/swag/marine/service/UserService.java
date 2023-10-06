package swag.marine.service;

import swag.marine.model.User;

public interface UserService {
    boolean addUser(User user);
    User getUser(String userId);
    boolean updateUser(User user);
    boolean passwordCheck(User user);
    boolean idDuplicateCheck(String userId);
}
