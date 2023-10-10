package swag.marine.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.ProductMapper;
import swag.marine.model.Product;
import swag.marine.service.ProductService;

@Transactional
@Service("ProductService")
public class ProductServiceImpl  implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public Integer addProduct(Product product) {
        if(productMapper.addProduct(product) > 0){
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public Integer updateProduct(Product product) {
        if(productMapper.updateProduct(product) > 0){
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public Integer deleteProduct(int productId) {
        if(productMapper.deleteProduct(productId) > 0){
            return 1;
        }else{
            return 0;
        }
    }
}
