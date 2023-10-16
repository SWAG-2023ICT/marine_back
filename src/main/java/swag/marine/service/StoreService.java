package swag.marine.service;

import swag.marine.model.Store;
import swag.marine.model.vo.StoreVo;

import java.sql.SQLException;
import java.util.List;

public interface StoreService {
    boolean addStore(Store store);
    StoreVo findStoreById(String storeId) throws SQLException;
    List<Store> getAllStores(Integer page);
    List<Store> findStoreByKeyword(String keyword);
    List<StoreVo> findStoreByUserId(String userId) throws SQLException;
}
