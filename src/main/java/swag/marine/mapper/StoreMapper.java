package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Store;

@Mapper
public interface StoreMapper {
    Integer addStore(Store store);
}
