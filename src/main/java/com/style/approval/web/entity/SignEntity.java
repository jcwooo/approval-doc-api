package com.style.approval.web.entity;

import com.style.approval.web.entity.common.CommonEntity;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import lombok.*;

import javax.persistence.*;

/**
 * document(문서) entity
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sign")
public class SignEntity extends CommonEntity {

    @MapsId("documentId")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="DOC_ID")
    private DocumentEntity document;

    @MapsId("userId")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private UserEntity user;

    @EmbeddedId
    private SignId signId;

    @Column(name="SIGN_NO",nullable = false)
    private int signNo;


    @Column(name="SIGN_STATUS")
    private String signStatus;
    private String opinion;



    public static SignEntity create(DocumentEntity documentEntity, UserEntity userEntity, SignId signId, SignModel signModel){
        return new SignEntity(documentEntity, userEntity, signId, signModel.getSignNo(), signModel.getSignStatus(), signModel.getOpinion());
    }




}
