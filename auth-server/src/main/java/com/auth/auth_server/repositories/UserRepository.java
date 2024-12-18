package com.auth.auth_server.repositories;

import com.auth.auth_server.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {


    Optional<UserEntity>findByUsername(String username);









}
