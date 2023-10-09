package swag.marine.service;

import org.springframework.stereotype.Component;
import swag.marine.model.Price;

import java.util.List;

@Component
public interface PriceService {
    Integer addPrice(Price price);
    Integer updatePrice(Price price);
    Integer deletePrice(int priceId);
    List<Price> selectAllPriceByProductId(int productId);
    Integer deleteAllPriceByProductId(int productId);
}
