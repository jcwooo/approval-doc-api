package com.style.approval.web.service.document;

import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;

import java.util.List;

public interface DocumentService {

    /**
     * 문서 생성
     * @param documentModel
     * @return
     */
    DocumentModel saveDocument(DocumentModel documentModel);

    /**
     * 유저가 등록한 결재 진행 중인 문서 조회
     * @param documentModel
     * @return
     */
    List<DocumentModel> getUserOutboxList(DocumentModel documentModel);

    /**
     * 유저가 결재해야할 문서 조회
     * @param userModel
     * @return
     */
    List<DocumentModel> getUserInboxList(UserModel userModel);

    /**
     * 문서 보기
     * @param documentModel
     * @return
     */
    DocumentModel selectDocument(DocumentModel documentModel);

    /**
     * 유저가 관여한 문서 중 결재가 완료 된 문서(archive)
     * @param userModel
     * @return
     */
    List<DocumentModel> getUserArchive(UserModel userModel);
}
