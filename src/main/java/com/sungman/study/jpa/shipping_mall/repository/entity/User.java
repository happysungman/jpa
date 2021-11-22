package com.sungman.study.jpa.shipping_mall.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String city;
    private String street;
    private String zipCode;
}
