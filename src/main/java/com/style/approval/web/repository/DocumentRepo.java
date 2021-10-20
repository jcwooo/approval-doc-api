package com.style.approval.web.repository;

import com.style.approval.web.entity.DocumentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepo extends JpaRepository<DocumentEntity, Long> {
    Optional<DocumentEntity> findById(long docId);


    List<DocumentEntity> findAllByUserIdAndDocStatus(String writerId, String docStatus);
}
