package swag.marine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swag.marine.model.Order;
import swag.marine.service.OrderService;


@RequestMapping("/marine/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{userId}/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String userId, @PathVariable Integer orderId){
        if(orderId == null) return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByUserId(userId));

        return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrderById(orderId));
    }
    @PostMapping("")
    public ResponseEntity<?> addOrders(@RequestBody Order order){
        boolean flag = orderService.addOrders(order);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}
