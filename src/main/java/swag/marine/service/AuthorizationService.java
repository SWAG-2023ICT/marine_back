package swag.marine.service;

import swag.marine.model.Store;
import swag.marine.model.User;

public interface AuthorizationService {
    boolean addUser(User user);
    boolean addStore(Store store);
    boolean idDuplicateCheck(String userId);
    boolean login(User user);
}
