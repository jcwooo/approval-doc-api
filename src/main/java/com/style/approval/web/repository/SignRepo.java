package com.style.approval.web.repository;

import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.SignEntity;
import com.style.approval.web.entity.SignId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignRepo extends JpaRepository<SignEntity, String> {

    Optional<SignEntity> findBySignId(SignId signId);
}
