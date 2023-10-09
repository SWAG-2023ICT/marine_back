package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Product;

@Mapper
public interface ProductMapper {
    Integer addProduct(Product product);
    Integer updateProduct(Product product);
    Integer deleteProduct(int product_id);

}
