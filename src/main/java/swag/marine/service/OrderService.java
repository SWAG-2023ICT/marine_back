package swag.marine.service;

import swag.marine.model.Order;

import java.util.List;

public interface OrderService {
    Order findOrderById(int orderId);
    List<Order> findOrdersByUserId(String userId);
    boolean addOrders(Order order);
}
