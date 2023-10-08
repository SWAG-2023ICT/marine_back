package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Price;

@Mapper
public interface PriceMapper {
    Integer addPrice(Price price);
    Integer updatePrice(Price price);
    Integer deletePrice(int price_id);

}
