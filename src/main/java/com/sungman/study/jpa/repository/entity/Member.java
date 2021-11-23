package com.sungman.study.jpa.repository.entity;

import com.sungman.study.jpa.enums.UserType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@Table(name = "member")
//@Table(name = "member", uniqueConstraints = {@UniqueConstraint(name = "name_age_unique", columnNames = {"name", "age"})})
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    /**
     * GenerationType.IDENTITY : 오라클, H2, DB2, PostgreSQL 에서 사용 가능
     * 유일한 값을 순서대로 생성해주는 특별한 DB 오브젝트
     */
//    @SequenceGenerator(
//            name = "board_seq_generator",
//            sequenceName = "board_seq", // 매핑할 DB 시퀀스 명
//            initialValue = 1,
//            allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")

    /**
     * GenerationType.TABLE : 키 생성 전용 테이블을 만들고 여기에 이름과 값으로 사용할 컬럼을 만들어 DB 시퀀스 흉내낸 전략
     */
//    create table MY_SEQUENCES {
//        sequence_name varchar(255) not null,
//        next_val bight,
//        primary key (sequence_name)
//    }
//    @TableGenerator(
//            name = "board_seq_generator",
//            table = "my_sequences",
//            pkColumnValue = "board_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "board_seq_generator")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updated;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @Transient // 매핑 안하도록
    private String temp;

    public static Member of(String name, Team team) {
        return new Member(null, name, null, UserType.USER, new Date(), LocalDateTime.now(), null, team, null);
    }

    public static Member of(String name) {
        return new Member(null, name, null, UserType.USER, new Date(), LocalDateTime.now(), null, null, null);
    }
}
