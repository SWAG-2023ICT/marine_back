package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.mapper.OrderMapper;
import swag.marine.mapper.PriceMapper;
import swag.marine.model.Order;
import swag.marine.model.OrderDetail;
import swag.marine.model.Price;
import swag.marine.model.Product;
import swag.marine.service.OrderService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final PriceMapper priceMapper;
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

    @Transactional
    @Override
    public boolean addOrders(Order order) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Product p : order.getProducts()){
            Price price = priceMapper.findPriceById(p.getPrices().get(0).getPriceId());
            order.setTotalPrice(price.getPriceByUnit() * p.getAmount());
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

    @Override
    public boolean updateOrderStatus(Order order) {
        Integer result = orderMapper.updateOrderStatus(order);
        return result > 0;
    }

    @Override
    public boolean addCanceledOrder(Order order) {
        Integer result = orderMapper.addCanceledOrders(order);
        return result > 0;
    }
}
