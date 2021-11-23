package com.sungman.study.jpa.repository;

import com.sungman.study.jpa.repository.entity.Member;
import com.sungman.study.jpa.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql(value = "member_repository_test.sql")
@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 양방향_참조_조회_테스트() {
        Team team = teamRepository.findById(1).orElse(null);

        List<Member> members = team.getMembers();

        assertAll(
                () -> assertTrue(members.size() == 1),
                () -> assertEquals("김성만", members.get(0).getName())
        );
    }

    @Test
    void 연관관계_주인_아니면_값_변경_불가_테스트() {
        Member member1 = Member.of("김성만1");
        Member member2 = Member.of("김성만2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Team team = Team.of("신생팀");
        team.getMembers().add(member1);
        team.getMembers().add(member2);
        teamRepository.save(team);

        assertAll(
                () -> assertNull(member1.getTeam()),
                () -> assertNull(member2.getTeam())
        );
    }
}