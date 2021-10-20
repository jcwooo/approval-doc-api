package com.style.approval.web.entity;

import com.style.approval.web.entity.common.CommonEntity;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.UserModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * document(문서) entity
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document")
public class DocumentEntity extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DOC_ID")
    private long id;
    private String title;
    private String type;
    private String contents;
    @Column(name="DOC_STATUS")
    private String docStatus;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserEntity user;

    @OneToMany(mappedBy = "document")
    List<SignEntity> docSignEntityList;

    private DocumentEntity(String title, String type, String contents, String docStatus, UserEntity user){
        this.title = title;
        this.type = type;
        this.contents = contents;
        this.docStatus = docStatus;
        this.user = user;
    }

    private DocumentEntity(UserEntity user, String docStatus ){
        this.user = user;
        this.docStatus = docStatus;
    }

    public static DocumentEntity create(DocumentModel documentModel, UserEntity user) {
        return new DocumentEntity(documentModel.getTitle(),documentModel.getType(), documentModel.getContents(), documentModel.getDocStatus(), user);
    }

}
