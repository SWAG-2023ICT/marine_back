package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.StoreMapper;
import swag.marine.mapper.UserMapper;
import swag.marine.model.Store;
import swag.marine.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    private final UserMapper userMapper;
    @Transactional
    @Override
    public boolean addStore(Store store) {
        Integer result = userMapper.addUser(store);
        if(result > 0) {
            store.setSellerId(store.getUserId());
            result = storeMapper.addStore(store);
        }
        return result > 0;
    }
    @Override
    public Store findStoreById(String storeId) {
        return storeMapper.getStoreById(storeId);
    }
    @Override
    public List<Store> getAllStores(int page) {
        return storeMapper.getAllStores(page);
    }
}
