package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.StoreMapper;
import swag.marine.model.Store;
import swag.marine.service.StoreService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    @Transactional
    @Override
    public boolean addStore(Store store) {
        store.setSellerId(store.getUserId());
        Integer result = storeMapper.addStore(store);
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
