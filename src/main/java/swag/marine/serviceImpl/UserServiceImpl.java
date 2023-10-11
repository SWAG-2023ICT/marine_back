package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.DestinationMapper;
import swag.marine.mapper.UserMapper;
import swag.marine.model.Destination;
import swag.marine.model.User;
import swag.marine.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final DestinationMapper destinationMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public boolean addUser(User user) {
        Integer result = 0;
        if(!idDuplicateCheck(user.getUserId())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(userMapper.addUser(user) > 0){
                Destination destination = user.getDestinations().get(0);
                destination.setUserId(user.getUserId());
                result = destinationMapper.addDestination(destination);
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
        User user = userMapper.getUser(loginUser.getUserId());
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


}
