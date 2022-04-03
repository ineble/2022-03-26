package com.sjs.exam.jpaBoard.user.dao;

import com.sjs.exam.jpaBoard.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByemail(String email);
}
