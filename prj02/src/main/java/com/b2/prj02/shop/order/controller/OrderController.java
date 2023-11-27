package com.b2.prj02.shop.order.controller;

import com.b2.prj02.shop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<?> findMyOrderDetail(@RequestHeader("X-AUTH-TOKEN") String token){
        return orderService.findMyOrderDetail(token);
    }

    @GetMapping("/total")
    public ResponseEntity<?> findMyOrder(@RequestHeader("X-AUTH-TOKEN") String token){
        return orderService.findMyOrder(token);
    }
}
