package swag.marine.mapper;

import org.apache.ibatis.annotations.Mapper;
import swag.marine.model.Order;
import swag.marine.model.OrderDetail;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order getOrderByOrderId(int orderId);
    List<Order> getOrdersById(String userId);
    Integer addOrder(Order order);
    Integer addOrderDetail(List<OrderDetail> orderDetails);
}
