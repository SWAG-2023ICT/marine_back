package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.marine.common.util.ConvertVo;
import swag.marine.mapper.StoreMapper;
import swag.marine.model.Store;
import swag.marine.model.vo.StoreVo;
import swag.marine.service.StoreService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {
    private final StoreMapper storeMapper;
    private final ConvertVo convertVo;
    @Override
    public boolean addStore(Store store) {
        store.setSellerId(store.getUserId());
        Integer result = storeMapper.addStore(store);
        return result > 0;
    }
    @Override
    public StoreVo findStoreById(String storeId) throws SQLException {
        return convertVo.convert(storeMapper.getStoreById(storeId));
    }
    @Override
    public List<Store> getAllStores(Integer page) {
        return storeMapper.getAllStores(page);
    }
    @Override
    public List<Store> findStoreByKeyword(String keyword) {
        return storeMapper.getStoreByKeyword(keyword);
    }
    @Override
    public List<StoreVo> findStoreByUserId(String userId) throws SQLException {
        List<StoreVo> storeVoList = new ArrayList<>();
        List<Store> stores = storeMapper.findStoreByUserId(userId);
        for(Store store : stores) storeVoList.add(convertVo.convert(store));
        return storeVoList;
    }
}
