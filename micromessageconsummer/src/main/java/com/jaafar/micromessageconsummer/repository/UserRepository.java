package com.jaafar.micromessageconsummer.repository;

import com.jaafar.micromessageconsummer.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
