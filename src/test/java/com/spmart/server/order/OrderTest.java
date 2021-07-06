package com.spmart.server.order;

import com.spmart.server.auth.domain.User;
import com.spmart.server.auth.repository.UserRepository;
import com.spmart.server.order.domain.Order;
import com.spmart.server.order.domain.OrderProduct;
import com.spmart.server.order.repository.OrderProductRepository;
import com.spmart.server.order.repository.OrderRepository;
import com.spmart.server.product.domain.Category;
import com.spmart.server.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
class OrderTest {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("Order 생성")
    public void OrderCreate() {
        Category category = Category.builder()
                .name("카테고리")
                .build();

        Product product1 = Product.builder()
                .code("남405-2")
                .name("원목 니켈스카시상패")
                .image("asdfdsa")
                .category(category) // fk
                .isDisplay(true)
                .isStock(true)
                .price(10000)
                .discountPrice(5000)
                .build();

        Product product2 = Product.builder()
                .code("남405-3")
                .name("하이후에")
                .image("asdfdsa")
                .category(category) // fk
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

        userRepository.save(user);

        OrderProduct orderProduct = OrderProduct.builder()
                .product(product1)
                .count(2)
                .build();

        List<OrderProduct> orderProductList = new ArrayList<>();
        orderProductList.add(orderProduct);

        Order order = Order.builder()
                .orderProduct(orderProductList)
                .user(user)
                .build();


//        OrderProduct savedOrder = orderProductRepository.save(orderProduct);
        Order savedOrder = orderRepository.save(order);

        System.out.println(order.getOrderProduct().size());
    }
}
