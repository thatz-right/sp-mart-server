package com.spmart.server.order.dto;

import com.spmart.server.auth.dto.UserInfo;
import com.spmart.server.common.BaseTimeEntity;
import com.spmart.server.order.domain.OrderStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfo extends BaseTimeEntity {
    private Long id;
    private UserInfo user;
    private List<OrderProductInfo> orderProducts;
    private OrderStatus orderStatus;
    private int totalPrice;
}
