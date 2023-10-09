package swag.marine.service;

import swag.marine.model.Product;

import java.util.List;

public interface OrderService {
    void addOrders(List<Product> products);
}
