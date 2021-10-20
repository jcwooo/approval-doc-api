package com.style.approval.web.service.document.impl;

import com.style.approval.advice.customException.CDocIdIsNotExistException;
import com.style.approval.advice.customException.CUserIdIsNotExistException;
import com.style.approval.advice.customException.ExceptionType;
import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.SignEntity;
import com.style.approval.web.entity.UserEntity;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.repository.DocumentRepo;
import com.style.approval.web.repository.SignRepo;
import com.style.approval.web.repository.UserRepo;
import com.style.approval.web.service.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final UserRepo userRepo;
    private final DocumentRepo documentRepo;
    private final SignRepo signRepo;

    /**
     * 문서 생성.
     * @param documentModel
     * @return
     */
    @Transactional
    @Override
    public DocumentModel saveDocument(DocumentModel documentModel) {
        UserModel resultModel;
        UserEntity userEntity = userRepo.findById(documentModel.getWriterId())
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));

        documentModel.setDocStatus("READY"); //결재 대기
        DocumentEntity documentEntity = DocumentEntity.create(documentModel,userEntity);

        return DocumentModel.of(documentRepo.save(documentEntity));
    }

    /**
     * 유저가 등록한 결재 진행 중인 문서 조회(outbox)
     * @param documentModel
     * @return
     */
    @Override
    public List<DocumentModel> getUserOutboxList(DocumentModel documentModel) {
        UserEntity userEntity = userRepo.findById(documentModel.getWriterId())
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));

        documentModel.setDocStatus("PROGRESS");
        List<DocumentEntity> userOutboxList = documentRepo.findAllByUserIdAndDocStatus(documentModel.getWriterId(),documentModel.getDocStatus());
        return DocumentModel.setEntityList(userOutboxList);
    }

    /**
     * 유저가 결재해야할 문서 조회(inbox)
     * @param userModel
     * @return
     */
    @Override
    public List<DocumentModel> getUserInboxList(UserModel userModel) {
        String userId = userModel.getUserId() ;

        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));


        List<SignEntity> signEntityList = userEntity.getUserSignEntityList().stream()
                                                                            .filter(s -> "PROGRESS".equals(s.getSignStatus()) && "PROGRESS".equals(s.getDocument().getDocStatus()))
                                                                            .collect(Collectors.toList());
        List<DocumentEntity> userInboxList = new ArrayList<>();
        signEntityList.stream().forEach(s -> userInboxList.add(s.getDocument()));
        return DocumentModel.setEntityList(userInboxList);
    }

    /**
     * 유저가 관여한 문서 중 결재가 완료 된 문서(archive)
     * @param userModel
     * @return
     */
    @Override
    public List<DocumentModel> getUserArchive(UserModel userModel) {
        String userId = userModel.getUserId() ;
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));

        List<SignEntity> signEntityList = userEntity.getUserSignEntityList().stream()
                                                                            .filter(s -> "APPROVAL".equals(s.getSignStatus()) || "REFUSE".equals(s.getSignStatus()))
                                                                            .filter(s -> "APPROVAL".equals(s.getDocument().getDocStatus()) || "REFUSE".equals(s.getDocument().getDocStatus()))
                                                                            .collect(Collectors.toList());
        List<DocumentEntity> userArchiveList = new ArrayList<>();
        signEntityList.stream().forEach(s -> userArchiveList.add(s.getDocument()));
        return DocumentModel.setEntityList(userArchiveList);
    }


    /**
     * 문서 보기
     * @param documentModel
     * @return
     */
    @Override
    public DocumentModel selectDocument(DocumentModel documentModel) {
        DocumentEntity documentEntity = documentRepo.findById(documentModel.getDocId())
                .orElseThrow(() -> new CDocIdIsNotExistException(ExceptionType.DOCUMENT_ID_IS_NOT_EXIST));
        return DocumentModel.of(documentEntity);
    }



}
