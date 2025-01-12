package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swag.marine.common.util.ConvertVo;
import swag.marine.mapper.DestinationMapper;
import swag.marine.mapper.OrderMapper;
import swag.marine.mapper.PriceMapper;
import swag.marine.model.Order;
import swag.marine.model.OrderDetail;
import swag.marine.model.Price;
import swag.marine.model.Product;
import swag.marine.model.vo.OrderVo;
import swag.marine.service.OrderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final PriceMapper priceMapper;
    private final DestinationMapper destinationMapper;
    private final ConvertVo convertVo;
    @Override
    public Order getOrdersByOrderId(int orderId) {
        return orderMapper.getOrdersByOrderId(orderId);
    }
    @Override
    public List<OrderVo> getOrdersByUsersId(String userId) throws SQLException {
        List<Order> orders = orderMapper.getOrdersByUsersId(userId);
        for(Order order : orders){
            order.setDestination(destinationMapper.getDestinationById(order.getDestinationId()));
            if(order.getOrderStatus() == 3){
                Order tmp = orderMapper.getCanceledOrders(order.getOrdersId());
                order.setReason(tmp.getReason());
                order.setCanceledDtm(tmp.getCanceledDtm());
            }
        }
        List<OrderVo> orderVoList = new ArrayList<>();
        for(Order order : orders){
            orderVoList.add(convertVo.convert(order));
        }
        return orderVoList;
    }
    @Override
    public List<OrderVo> getOrdersByStoreId(String storeId) throws SQLException {
        List<Order> orders = orderMapper.getOrdersByStoreId(storeId);
        for(Order order : orders){
            order.setDestination(destinationMapper.getDestinationById(order.getDestinationId()));
            if(order.getOrderStatus() == 3){
                Order tmp = orderMapper.getCanceledOrders(order.getOrdersId());
                order.setReason(tmp.getReason());
                order.setCanceledDtm(tmp.getCanceledDtm());
            }
        }
        List<OrderVo> orderVoList = new ArrayList<>();
        for(Order order : orders){
            orderVoList.add(convertVo.convert(order));
        }
        return orderVoList;
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
                        .priceId(p.getPrices().get(0).getPriceId())
                        .build()
                );
            }
            result = orderMapper.addOrderDetail(orderDetails);
        }
        return result == order.getProducts().size();
    }

    @Override
    public boolean updateOrderStatus(Order order) {
        Integer result = 0;
        if(order.getOrderStatus() == 3){
            if(addCanceledOrder(order)){
                order.setOrderStatus(3);
                if(orderMapper.updateOrderStatus(order) > 0){
                    order.setOrderStatus(0);
                    result = orderMapper.updateDeliveryStatus(order);
                }
            }
        } else if(order.getOrderStatus() == 1 || order.getOrderStatus() == 2) {
            order.setDeliveryStatus(2);
            result = orderMapper.updateDeliveryStatus(order);
        }
        return result > 0;
    }
    @Override
    public boolean addCanceledOrder(Order order) {
        Integer result = orderMapper.addCanceledOrders(order);
        return result > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        Integer result = orderMapper.updateOrder(order);
        if(result > 0){
            order.setDeliveryStatus(3);
            result = orderMapper.updateDeliveryStatus(order);
        }
        return result > 0;
    }
}
