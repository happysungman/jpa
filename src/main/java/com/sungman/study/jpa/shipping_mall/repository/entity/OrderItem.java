package com.sungman.study.jpa.shipping_mall.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue
    private long id;
    private long orderId;
    private long itemId;
    private long orderPrice;
    private int count;
}
