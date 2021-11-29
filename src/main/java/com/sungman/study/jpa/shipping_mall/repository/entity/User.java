package com.sungman.study.jpa.shipping_mall.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;
    private String street;
    private String zipCode;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public static User of(final String name) {
        return new User(null, name, null, null, null, new ArrayList<>());
    }
}
