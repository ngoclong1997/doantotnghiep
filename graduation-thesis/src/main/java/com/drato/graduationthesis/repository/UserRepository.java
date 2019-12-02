package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	Optional<User> findById(Long id);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	User findByEmail(String email);
}
