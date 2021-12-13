package com.spmart.server.order.controller;

import com.spmart.server.common.dto.StatusMessage;
import com.spmart.server.common.exception.StatusCode;
import com.spmart.server.order.dto.OrderRequest;
import com.spmart.server.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<StatusMessage> create(@RequestBody OrderRequest orderRequest) {
        orderService.save(orderRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusMessage(StatusCode.SUCCESS));
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<StatusMessage> update(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest) {
        orderService.changeOrderStatus(orderId, orderRequest.getOrderStatus());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StatusMessage(StatusCode.SUCCESS));
    }
}
