package com.sungman.study.jpa.shipping_mall.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private int stockQuantity;

    public static Item of(String name, long price, int stockQuantity) {
        return new Item(null, name, price, stockQuantity);
    }
}
