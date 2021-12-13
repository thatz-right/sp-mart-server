package com.spmart.server.order.service;

import com.spmart.server.order.domain.OrderStatus;
import com.spmart.server.order.dto.OrderRequest;

public interface OrderService {
    void save(OrderRequest orderRequest);
    void changeOrderStatus(Long orderId, OrderStatus orderStatus);
}
