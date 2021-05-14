package com.sungman.study.jpa.repository;

import com.sungman.study.jpa.repository.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 멤버_저장_테스트() {
        Member member = Member.of("김성만", 1L);
        Member saved = memberRepository.save(member);
        Member found = memberRepository.findById(member.getId()).orElse(null);

        assertAll(
                () -> assertEquals(member.getId(), saved.getId()),
                () -> assertEquals(member.getName(), saved.getName()),
                () -> assertEquals(member.getTeamId(), saved.getTeamId()),

                () -> assertNotNull(found),
                () -> assertEquals(member.getId(), found.getId()),
                () -> assertEquals(member.getName(), found.getName()),
                () -> assertEquals(member.getTeamId(), found.getTeamId())
        );
    }

}