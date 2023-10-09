package swag.marine.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.PriceMapper;
import swag.marine.model.Price;
import swag.marine.service.PriceService;

import java.util.List;

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
    public Integer deletePrice(int priceId) {
        if(priceMapper.deletePrice(priceId) > 0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public List<Price> selectAllPriceByProductId(int productId) {
        return priceMapper.selectAllPriceByProductId(productId);
    }

    @Override
    public Integer deleteAllPriceByProductId(int productId) {
        if(priceMapper.deleteAllPriceByProductId(productId) > 0){
            return 1;
        }else{
            return 0;
        }
    }
}
