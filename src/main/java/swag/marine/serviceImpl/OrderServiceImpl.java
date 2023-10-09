package swag.marine.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swag.marine.mapper.OrderMapper;
import swag.marine.model.Product;
import swag.marine.service.OrderService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;


    @Override
    public void addOrders(List<Product> products) {
    }
}
