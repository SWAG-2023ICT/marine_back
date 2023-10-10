package swag.marine.service;

import swag.marine.model.Order;

import java.util.List;

public interface OrderService {
    Order getOrderByOrderId(int orderId);
    List<Order> getOrdersById(String userId);
    boolean addOrders(Order order);
}
