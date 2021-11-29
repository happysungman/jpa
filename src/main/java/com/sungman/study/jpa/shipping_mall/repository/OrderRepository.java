package com.sungman.study.jpa.shipping_mall.repository;

import com.sungman.study.jpa.shipping_mall.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
