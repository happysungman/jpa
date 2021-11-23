package com.sungman.study.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "team")
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    public static Team of(Long id, String name) {
        return new Team(id, name, new ArrayList<>());
    }

    public static Team of(String name) {
        return new Team(null, name, new ArrayList<>());
    }
}
