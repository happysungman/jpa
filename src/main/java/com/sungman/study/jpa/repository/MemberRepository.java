package com.sungman.study.jpa.repository;

import com.sungman.study.jpa.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(long id);

    List<Member> findAllByTeamId(long teamId);

    long countById(@NonNull Long id);



}
