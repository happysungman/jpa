package com.sungman.study.jpa.shipping_mall.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "user_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setUser(User user) {

        if (Objects.nonNull(this.user)) {
            this.user.getOrders().remove(this);
        }
        this.user = user;
        user.getOrders().add(this);
    }

    public static Order of(User user, List<OrderItem> orderItems, OrderStatus orderStatus) {
        return new Order(null, user, orderItems, LocalDateTime.now(), orderStatus);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public enum OrderStatus {
        ORDER, CANCEL
    }
}
