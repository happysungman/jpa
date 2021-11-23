package com.sungman.study.jpa.repository;

import com.sungman.study.jpa.repository.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findById(long id);
}
