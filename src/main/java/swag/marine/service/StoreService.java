package swag.marine.service;

import swag.marine.model.Store;

import java.util.List;

public interface StoreService {
    boolean addStore(Store store);
    Store findStoreById(String storeId);
    List<Store> getAllStores(Integer page);
}
