package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.marine.mapper.OrderMapper;
import swag.marine.model.Order;
import swag.marine.model.OrderDetail;
import swag.marine.model.Product;
import swag.marine.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    @Override
    public Order getOrdersByOrderId(int orderId) {
        return orderMapper.getOrdersByOrderId(orderId);
    }
    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderMapper.getOrdersByUserId(userId);
    }
    @Override
    public List<Order> getOrdersByStoreId(String storeId) {
        return orderMapper.getOrdersByStoreId(storeId);
    }
    @Override
    public boolean addOrders(Order order) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Product p : order.getProducts()){
            order.setTotalPrice(p.getPrices().get(0).getPriceByUnit() * p.getAmount());
        }
        Integer result = orderMapper.addOrder(order);
        if(result > 0){
            for(Product p : order.getProducts()){
                orderDetails.add(
                        OrderDetail.builder()
                        .ordersId(order.getOrdersId())
                        .productId(p.getProductId())
                        .amount(p.getAmount())
                        .build()
                );
            }
            result = orderMapper.addOrderDetail(orderDetails);
        }
        return result == order.getProducts().size();
    }
}
