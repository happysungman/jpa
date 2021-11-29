package com.sungman.study.jpa.shipping_mall.repository;

import com.sungman.study.jpa.shipping_mall.repository.entity.Item;
import com.sungman.study.jpa.shipping_mall.repository.entity.Order;
import com.sungman.study.jpa.shipping_mall.repository.entity.OrderItem;
import com.sungman.study.jpa.shipping_mall.repository.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ObjectGraphTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void 객체_그래프_탐색_테스트() {
//        User user = User.of("김성만");
//        Order order = Order.of(user, null, Order.OrderStatus.ORDER);
//        Item item = Item.of("좋은상품", 10000, 10);
//        OrderItem orderItem = OrderItem.of(order, item, 10000, 1);
//
//        userRepository.save(user);
//        orderRepository.save(order);
//        itemRepository.save(item);
//        orderItemRepository.save(orderItem);

        User foundUser = userRepository.findById(1L).orElse(null);
        OrderItem orderItem = orderItemRepository.findById(1L).orElse(null);


        assertEquals("좋은상품", orderItem.getItem().getName());

        // TODO : 왜 연관관계의 주인인 oderItem을 저장헀는데도, user를 읽을 때 user.getOrders를 가져오지 못하는가?
        //  추측 : DB에 쿼리를 날리지 않고, 영속성 컨텍스트에 저장된 그대로 가져오는 것 같다
        assertEquals("좋은상품", foundUser.getOrders().get(0).getOrderItems().get(0).getItem().getName());

    }

    @BeforeEach
    void init() {
        User user = User.of("김성만");
        Order order = Order.of(user, Collections.emptyList(), Order.OrderStatus.ORDER);
        Item item = Item.of("좋은상품", 10000, 10);
        OrderItem orderItem = OrderItem.of(order, item, 10000, 1);

        userRepository.save(user);
        orderRepository.save(order);
        itemRepository.save(item);
        orderItemRepository.save(orderItem);
    }

}