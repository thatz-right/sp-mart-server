package com.spmart.server.order.dto;

import com.spmart.server.auth.domain.User;
import com.spmart.server.auth.dto.UserInfo;
import com.spmart.server.order.domain.Order;
import com.spmart.server.order.domain.OrderStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
    private Long id;
    private UserInfo user;
    private List<OrderProductInfo> orderProducts;
    private OrderStatus orderStatus;
    private int totalPrice;

    public Order toEntity() {
        return Order.builder()
                .id(id)
                .user(User.builder().id(user.getId()).build())
                .build();
    }
}





