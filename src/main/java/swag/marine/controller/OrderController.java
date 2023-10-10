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
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable String id){
        Integer orderId = null;
        try {
            orderId = Integer.parseInt(id);
        } catch (NumberFormatException exception){
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersById(id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByOrderId(orderId));
    }
    @PostMapping("")
    public ResponseEntity<?> addOrders(@RequestBody Order order){
        boolean flag = orderService.addOrders(order);
        if(flag) return ResponseEntity.status(HttpStatus.OK).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}
