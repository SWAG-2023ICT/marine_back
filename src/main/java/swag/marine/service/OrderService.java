package swag.marine.service;

import swag.marine.model.Order;
import swag.marine.model.vo.OrderVo;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    Order getOrdersByOrderId(int orderId);
    List<OrderVo> getOrdersByUsersId(String userId) throws SQLException;
    List<OrderVo> getOrdersByStoreId(String storeId) throws SQLException;
    boolean addOrders(Order order);
    boolean updateOrderStatus(Order order);
    boolean addCanceledOrder(Order order);
    boolean updateOrder(Order order);
}
