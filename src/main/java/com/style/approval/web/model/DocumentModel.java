package com.style.approval.web.model;

import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * UserModel
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentModel {
    private long docId; //문서번호
    private String title; //문서 제목
    private String type; //문서 분류
    private String contents; //문서 내용
    private String writerId; //작성자 ID
    private String docStatus; //상태

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static DocumentModel setEntity(DocumentEntity document) {
        return DocumentModel.builder()
                .docId(document.getId())
                .title(document.getTitle())
                .type(document.getType())
                .contents(document.getContents())
                .writerId(document.getUser().getId())
                .docStatus(document.getDocStatus())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }


    public static List<DocumentModel> setEntityList(List<DocumentEntity> documents) {
        return documents.stream().map(DocumentModel::setEntity).collect(toList());
    }

    //문서객체생성
    public static DocumentModel of(DocumentEntity document) {
        return DocumentModel.builder().docId(document.getId())
                .title(document.getTitle())
                .type(document.getType())
                .contents(document.getContents())
                .writerId(document.getUser().getId())
                .docStatus(document.getDocStatus())
                .createdAt(document.getCreatedAt())
                .updatedAt(document.getUpdatedAt())
                .build();
    }
}
