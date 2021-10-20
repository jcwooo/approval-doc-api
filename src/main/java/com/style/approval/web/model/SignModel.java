package com.style.approval.web.model;

import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.SignEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * UserModel
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignModel {
    private long docId; //결재문서id
    private String userId; //결재자id
    private int signNo; //결재순번
    private String signStatus; //결재상태
    private String opinion; //의견

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //결재객체생성
    public static SignModel of(SignEntity signEntity) {
        return SignModel.builder()
                .docId(signEntity.getDocument().getId())
                .userId(signEntity.getUser().getId())
                .signNo(signEntity.getSignNo())
                .signStatus(signEntity.getSignStatus())
                .opinion(signEntity.getOpinion())
                .createdAt(signEntity.getCreatedAt())
                .updatedAt(signEntity.getUpdatedAt())
                .build();
    }
}
