package com.b2.prj02.service.cart;

import com.b2.prj02.dto.cart.CartDTO;
import com.b2.prj02.dto.cart.RequestedCartItemDTO;

public interface CartService {
    CartDTO addCart(RequestedCartItemDTO cart, Long userId);
}
