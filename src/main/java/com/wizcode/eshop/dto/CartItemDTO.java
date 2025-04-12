package com.wizcode.eshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDTO product;
}
