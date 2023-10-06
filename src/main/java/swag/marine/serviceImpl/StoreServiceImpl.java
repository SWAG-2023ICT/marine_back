package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.marine.mapper.StoreMapper;
import swag.marine.model.Store;
import swag.marine.service.StoreService;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    @Override
    public boolean addStore(Store store) {
        Integer result = storeMapper.addStore(store);
        return result > 0;
    }
}
