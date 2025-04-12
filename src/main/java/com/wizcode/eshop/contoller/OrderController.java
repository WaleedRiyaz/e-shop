package com.wizcode.eshop.contoller;

import com.wizcode.eshop.dto.OrderDTO;
import com.wizcode.eshop.exception.ResourceNotFoundException;
import com.wizcode.eshop.model.Order;
import com.wizcode.eshop.response.APIResponse;
import com.wizcode.eshop.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    private final IOrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<APIResponse> createOrder(@RequestParam Long userId) {
        try {
            Order order =  orderService.placeOrder(userId);
            OrderDTO orderDTO = orderService.convertToDto(order);
            return ResponseEntity.ok(new APIResponse("Item Order Success!", orderDTO));
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse("Error Occurred!", e.getMessage()));
        }
    }

    @GetMapping("/{orderId}/order")
    public ResponseEntity<APIResponse> getOrderById(@PathVariable Long orderId) {
        try {
            OrderDTO order = orderService.getOrder(orderId);
            return ResponseEntity.ok(new APIResponse("Item Order Success!", order));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse("Oops!", e.getMessage()));
        }
    }

    @GetMapping("/{userId}/order")
    public ResponseEntity<APIResponse> getUserOrders(@PathVariable Long userId) {
        try {
            List<OrderDTO> order = orderService.getUserOrders(userId);
            return ResponseEntity.ok(new APIResponse("Item Order Success!", order));
        } catch (ResourceNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse("Oops!", e.getMessage()));
        }
    }
}
