package com.wizcode.eshop.service.cart;

import com.wizcode.eshop.model.Cart;
import com.wizcode.eshop.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserId(Long userId);
}
