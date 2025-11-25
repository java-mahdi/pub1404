package com.kh.testspring1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.testspring1.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
public Optional<UserModel> findByUsername(String username);
}
