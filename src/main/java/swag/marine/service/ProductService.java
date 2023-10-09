package swag.marine.service;

import org.springframework.stereotype.Component;
import swag.marine.model.Product;

@Component
public interface ProductService {
    Integer addProduct(Product product);
    Integer updateProduct(Product product);
    Integer deleteProduct(int productId);
}
