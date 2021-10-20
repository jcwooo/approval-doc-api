package com.style.approval.web.repository;

import com.style.approval.web.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findById(String id);
    Optional<UserEntity> findByIdAndPassword(String id, String password);
}
