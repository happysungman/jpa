package com.sungman.study.jpa.shipping_mall.repository;

import com.sungman.study.jpa.shipping_mall.repository.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
