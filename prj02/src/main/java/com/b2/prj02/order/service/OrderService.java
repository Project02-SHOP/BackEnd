package com.b2.prj02.order.service;

import com.b2.prj02.config.security.jwt.JwtTokenProvider;
import com.b2.prj02.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final JwtTokenProvider jwtTokenProvider;
    public ResponseEntity<?> findMyOrderDetail(String token) {
        String email = jwtTokenProvider.findEmailBytoken(token);

        if(email==null)
            throw new DisabledException("로그인을 다시 해주세요.");

        return ResponseEntity.status(200).body(orderRepository.findByUserEmail(email));
    }

    public ResponseEntity<?> findMyOrder(String token) {
        String email = jwtTokenProvider.findEmailBytoken(token);

        if(email==null)
            throw new DisabledException("로그인을 다시 해주세요.");

        return ResponseEntity.status(200).body(orderRepository.total(email));
    }
}
