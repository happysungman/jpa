package com.sungman.study.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "team")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static Team of(Long id, String name) {
        return new Team(id, name);
    }

    public static Team of(String name) {
        return new Team(null, name);
    }
}
