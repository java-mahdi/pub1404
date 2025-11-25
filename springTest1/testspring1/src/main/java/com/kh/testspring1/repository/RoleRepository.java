package com.kh.testspring1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.testspring1.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
public Optional<Role> findByName(String name);
}
