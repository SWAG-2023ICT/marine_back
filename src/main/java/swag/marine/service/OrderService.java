package swag.marine.service;

import swag.marine.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrdersByOrderId(int orderId);
    List<Order> getOrdersByUsersId(String userId);
    List<Order> getOrdersByStoreId(String storeId);
    boolean addOrders(Order order);
    boolean updateOrderStatus(Order order);
    boolean addCanceledOrder(Order order);
    boolean updateOrder(Order order);
}
