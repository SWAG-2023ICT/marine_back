package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.AuthorizationMapper;
import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.service.AuthorizationService;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthorizationMapper authorizationMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Integer addUser(User user) {
        Integer result = 0;
        if(!idDuplicateCheck(user.getUserId())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            result = authorizationMapper.addUser(user);
        }
        return result;
    }
    @Transactional
    @Override
    public Integer addStore(Store store) {
        Integer result = 0;
        if(addUser(store) > 0){
            store.setStoreId(store.getUserId());
            result = authorizationMapper.addStore(store);
        }
        return result;
    }
    @Override
    public boolean idDuplicateCheck(String userId) {
        return authorizationMapper.idDuplicateCheck(userId) > 0;
    }
}
