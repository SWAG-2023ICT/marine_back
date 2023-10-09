package swag.marine.service;

import org.springframework.stereotype.Component;
import swag.marine.model.Price;

@Component
public interface PriceService {
    Integer addPrice(Price price);
    Integer updatePrice(Price price);
    Integer deletePrice(int price_id);
}
