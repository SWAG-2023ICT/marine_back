package swag.marine.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.PriceMapper;
import swag.marine.model.Price;
import swag.marine.service.PriceService;

@Transactional
@Service("PriceService")
public class PriceServiceImpl implements PriceService {
    @Autowired
    PriceMapper priceMapper;

    @Override
    public Integer addPrice(Price price) {
        if(priceMapper.addPrice(price) > 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer updatePrice(Price price) {
        if(priceMapper.updatePrice(price) > 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer deletePrice(int price_id) {
        if(priceMapper.deletePrice(price_id) > 0){
            return 1;
        }else{
            return 0;
        }
    }
}
