package com.sungman.study.jpa.repository.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "member")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    public static Member of(String name, Team team) {
        return new Member(null, name, team);
    }
}
