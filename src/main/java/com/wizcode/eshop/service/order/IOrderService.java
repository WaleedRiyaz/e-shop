package com.wizcode.eshop.service.order;

import com.wizcode.eshop.dto.OrderDTO;
import com.wizcode.eshop.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDTO getOrder(Long orderId);
    List<OrderDTO> getUserOrders(Long userId);

    OrderDTO convertToDto(Order order);
}
