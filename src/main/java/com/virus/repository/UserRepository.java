package com.virus.repository;

import com.virus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);
    User getAllByEmail(String email);
    Boolean existsByEmail(String Email);
}
