package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Product;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer addOrder(List<Product> products);
}
