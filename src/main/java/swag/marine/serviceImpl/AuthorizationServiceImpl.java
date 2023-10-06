package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.AuthorizationMapper;
import swag.marine.mapper.DestinationMapper;
import swag.marine.model.Store;
import swag.marine.model.User;
import swag.marine.service.AuthorizationService;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final AuthorizationMapper authorizationMapper;
    private final DestinationMapper destinationMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean addUser(User user) {
        Integer result = 0;
        if(!idDuplicateCheck(user.getUserId())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(authorizationMapper.addUser(user) > 0){
                result = destinationMapper.addDestination(user.getDestinations().get(0));
            }
        }
        return result > 0;
    }
    @Transactional
    @Override
    public boolean addStore(Store store) {
        Integer result = 0;
        if(addUser(store)){
            store.setStoreId(store.getUserId());
            result = authorizationMapper.addStore(store);
        }
        return result > 0;
    }
    @Override
    public boolean idDuplicateCheck(String userId) {
        return authorizationMapper.idDuplicateCheck(userId) > 0;
    }

    @Override
    public boolean login(User loginUser) {
        User user = authorizationMapper.getUser(loginUser);
        return passwordEncoder.matches(loginUser.getPassword(), user.getPassword());
    }
}
