package swag.marine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "orders",description = "주문 관련 API")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId){
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

    @Operation(summary = "주문 추가",description = "주문을 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "주문이 정상적으로 추가되었습니다.",
            content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "주문 추가에 실패했습니다.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("")
    public ResponseEntity<?> addOrders(@RequestBody Order order){
        boolean flag = orderService.addOrders(order);
        if(flag) return ResponseEntity.status(HttpStatus.CREATED).body("success!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail!");
    }
}
