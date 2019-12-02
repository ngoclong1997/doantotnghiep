package com.drato.graduationthesis.repository;

import com.drato.graduationthesis.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
    public boolean existsByName(String name);
}
