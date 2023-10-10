package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Order;
import swag.marine.service.OrderService;

import java.util.List;

@RequestMapping("/marine/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{orderId}")
    public ResponseEntity<Order>getOrderByOrderId(@PathVariable Integer orderId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByOrderId(orderId));
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userId));
    }
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<List<Order>> getOrderByStoreId(@PathVariable String storeId){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByStoreId(storeId));
    }
    @PostMapping("")
    public ResponseEntity<?> addOrders(@RequestBody Order order){
        boolean flag = orderService.addOrders(order);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}
