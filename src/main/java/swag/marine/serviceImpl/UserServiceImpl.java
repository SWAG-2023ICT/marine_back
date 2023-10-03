package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.UserMapper;
import swag.marine.model.User;
import swag.marine.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Integer addUser(User user) {
        Integer result = 0;
        if(idDuplicateCheck(user.getUserId())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            result = userMapper.addUser(user);
        }
        return result;
    }
    @Override
    public boolean idDuplicateCheck(String userId) {
        return userMapper.idDuplicateCheck(userId) > 0;
    }
}
