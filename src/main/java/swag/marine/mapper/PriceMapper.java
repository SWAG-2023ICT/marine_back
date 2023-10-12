package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Price;

import java.util.List;

@Mapper
public interface PriceMapper {
    Integer addPrice(Price price);
    Integer updatePrice(Price price);
    Integer deletePrice(List<Price> prices);
    List<Price> selectAllPriceByProductId(int productId);
    Integer deleteAllPriceByProductId(int productId);
    Integer deletePriceByPriceId(int priceId);
}
