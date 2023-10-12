package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Price;

import java.util.List;

@Mapper
public interface PriceMapper {
    Integer addPrice(Price price);
    Integer updatePrice(Price price);
    Integer deletePrice(int priceId);
    List<Price> selectAllPriceByProductId(int productId);
    Integer deleteAllPriceByProductId(int productId);

}
