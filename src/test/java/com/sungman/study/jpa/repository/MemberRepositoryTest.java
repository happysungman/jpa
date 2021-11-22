package com.sungman.study.jpa.repository;

import com.sungman.study.jpa.repository.entity.Member;
import com.sungman.study.jpa.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Sql(value = "member_repository_test.sql")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 멤버_저장_테스트() {
        Team team = Team.of("프론트팀");
        Member member = Member.of("김성만", team);
        Member saved = memberRepository.save(member);
        Member found = memberRepository.findById(member.getId()).orElse(null);

        assertAll(
                () -> assertEquals(member.getId(), saved.getId()),
                () -> assertEquals(member.getName(), saved.getName()),
                () -> assertEquals(member.getTeam(), saved.getTeam()),

                () -> assertNotNull(found),
                () -> assertEquals(member.getId(), found.getId()),
                () -> assertEquals(member.getName(), found.getName()),

                () -> assertNotNull(found.getTeam()),
                () -> assertEquals(2, found.getTeam().getId()),
                () -> assertEquals("프론트팀", found.getTeam().getName())
        );

        System.out.println(member);
    }

    @Test
    void 멤버_조회_테스트() {
        Member member = memberRepository.findById(1L).orElse(null);
        assertAll(
                () -> assertNotNull(member),
                () -> assertEquals(1L, member.getId()),
                () -> assertEquals("김성만", member.getName())
        );

        Team team = member.getTeam();
        assertAll(

                () -> assertNotNull(team),
                () -> assertEquals(1L, team.getId()),
                () -> assertEquals("백엔드팀", team.getName())
        );

        System.out.println(member);
    }

}