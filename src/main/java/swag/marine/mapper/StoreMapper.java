package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Store;

import java.util.List;

@Mapper
public interface StoreMapper {
    Integer addStore(Store store);
    Store getStoreById(String storeId);
    List<Store> getAllStores(Integer page);
    List<Store> getStoreByWishStore(List<String> stringIds);
    List<Store> getStoreByKeyword(String keyword);
    List<Store> findStoreByUserId(String userId);
}
