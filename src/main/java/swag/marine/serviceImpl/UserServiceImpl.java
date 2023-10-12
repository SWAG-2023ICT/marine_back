package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.DestinationMapper;
import swag.marine.mapper.StoreMapper;
import swag.marine.mapper.UserMapper;
import swag.marine.mapper.WishMapper;
import swag.marine.model.Destination;
import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.model.Wish;
import swag.marine.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final StoreMapper storeMapper;
    private final DestinationMapper destinationMapper;
    private final WishMapper wishMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public boolean addUser(User user) {
        Integer result = 0;
        if (!idDuplicateCheck(user.getUserId())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (userMapper.addUser(user) > 0) {
                try {
                    List<Destination> destinations = user.getDestinations();
                    Destination destination = destinations.get(0);
                    destination.setUserId(user.getUserId());
                    if(destination.getDestinationName().isBlank()){
                        int num = destinationMapper.getDestinationCount(user.getUserId());
                        destination.setDestinationName("배송지-"+ num);
                    }
                    result = destinationMapper.addDestination(destination);
                    } catch (NullPointerException exception) {
                    return true;
                }
            }
        }
        return result > 0;
    }
    @Override
    public User getUser(String userId) {
        User user = userMapper.getUser(userId);
//        if(user == null) throw new RuntimeException(); custom exception 생성.
        return user;
    }
    @Transactional
    @Override
    public boolean updateUser(User loginUser) {
        Integer result = 0;
        if(passwordCheck(loginUser)){
            loginUser.setPassword(passwordEncoder.encode(loginUser.getPassword()));
            result = userMapper.updateUser(loginUser);
        }
        return result > 0;
    }
    @Override
    public boolean passwordCheck(User loginUser) {
        User user = userMapper.getLoginUser(loginUser.getUserId());
        // if(user == null) throw RuntimeException();
        return passwordEncoder.matches(loginUser.getPassword(), user.getPassword());
    }
    @Override
    public boolean idDuplicateCheck(String userId) {
        return userMapper.idDuplicateCheck(userId) > 0;
    }
    @Override
    public boolean isStore(String sellerId) {
        return userMapper.isStore(sellerId) > 0;
    }
    @Transactional
    @Override
    public boolean updatePassword(User user) {
        boolean check = passwordCheck(user);
        Integer result = 0;
        if(check){
            User newUser = User.builder()
                    .userId(user.getUserId())
                    .password(passwordEncoder.encode(user.getNewPassword()))
                    .build();
            result = userMapper.updatePassword(newUser);
        }
        return result > 0;
    }
    @Transactional
    @Override
    public boolean addWish(Wish wish) {
        Integer result = wishMapper.addWish(wish);
        return result > 0;
    }
    @Transactional
    @Override
    public boolean deleteWish(List<Integer> wishIds) {
        Integer result = wishMapper.deleteWish(wishIds);
        return result == wishIds.size();
    }
    @Override
    public List<Store> findAllWish(String userId){
        List<String> storeIds = wishMapper.findAllWish(userId);
        if(storeIds.size() == 0) return new ArrayList<>();
        return storeMapper.getStoreByWishStore(storeIds);
    }
}
