package com.spmart.server.order.service;

import com.spmart.server.auth.domain.User;
import com.spmart.server.order.domain.Order;
import com.spmart.server.order.domain.OrderProduct;
import com.spmart.server.order.domain.OrderStatus;
import com.spmart.server.order.dto.OrderRequest;
import com.spmart.server.order.repository.OrderRepository;
import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    }

    Order getOrder() {
        Category categoryItem = Category.builder()
                .name("카테고리")
                .build();

        Product product1 = Product.builder()
                .code("남405-2")
                .name("원목 니켈스카시상패")
                .image("asdfdsa")
                .category(categoryItem) // fk
                .isDisplay(true)
                .isStock(true)
                .price(10000)
                .discountPrice(5000)
                .build();

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email("asd@adsf.com")
                .phone("010-1111-2222")
                .name("tester")
                .address("서울시 서대문구") // 주소 넣는 api 검색 넣기
                .build();

        Order order = Order.builder()
                .id(1L)
                .user(user)
                .build();
        OrderProduct orderProduct = OrderProduct.builder()
                .product(product1)
                .order(order)
                .count(2)
                .build();

        order.addOrderProduct(orderProduct);
        return order;
    }

    @Test
    @DisplayName("오더 생성하기")
    public void registerOrder() {
        Order order = getOrder();

        OrderRequest orderRequest = modelMapper.map(order, OrderRequest.class);

        when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(order);

        orderService.save(orderRequest);

        verify(orderRepository).save(Mockito.any(Order.class));
    }

    @Test
    @DisplayName("오더 업데이트")
    public void updateOrder() {
        Order order = getOrder();
        OrderStatus orderStatus = OrderStatus.CONFIRMED;

        when(orderRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(order));
        when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(order);

        orderService.changeOrderStatus(order.getId(), orderStatus);

        verify(orderRepository).findById(Mockito.anyLong());
        verify(orderRepository).save(Mockito.any(Order.class));
    }
}
