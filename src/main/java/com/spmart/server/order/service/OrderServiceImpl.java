package com.spmart.server.order.service;

import com.spmart.server.order.domain.Order;
import com.spmart.server.order.domain.OrderStatus;
import com.spmart.server.order.dto.OrderRequest;
import com.spmart.server.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void save(OrderRequest orderRequest) {
        orderRepository.save(orderRequest.toEntity());
    }

    @Override
    public void changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);

        order.changeOrderStatus(orderStatus);

        orderRepository.save(order);
    }
}
