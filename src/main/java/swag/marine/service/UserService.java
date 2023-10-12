package swag.marine.service;

import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.model.Wish;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    User getUser(String userId);
    boolean updateUser(User user);
    boolean passwordCheck(User user);
    boolean idDuplicateCheck(String userId);
    boolean isStore(String sellerId);
    boolean updatePassword(User user);
    Integer addWish(Wish wish);
    boolean deleteWish(List<Integer> wishIds);
    List<Store> findAllWish(String userId);
    Integer checkWishStatus(String storeId, String userId);
}
