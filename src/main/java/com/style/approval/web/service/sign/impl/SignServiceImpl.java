package com.style.approval.web.service.sign.impl;

import com.style.approval.advice.customException.*;
import com.style.approval.web.entity.DocumentEntity;
import com.style.approval.web.entity.SignEntity;
import com.style.approval.web.entity.SignId;
import com.style.approval.web.entity.UserEntity;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.repository.DocumentRepo;
import com.style.approval.web.repository.SignRepo;
import com.style.approval.web.repository.UserRepo;
import com.style.approval.web.service.document.DocumentService;
import com.style.approval.web.service.sign.SignService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService{
    private final UserRepo userRepo;
    private final DocumentRepo documentRepo;
    private final SignRepo signRepo;

    /**
     * 결재 유저 생성
     * @param signModel
     * @return
     */
    @Transactional
    @Override
    public SignModel createSignUser(SignModel signModel) {
        UserModel resultModel;
        UserEntity userEntity = userRepo.findById(signModel.getUserId())
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));

        DocumentModel documentModel;
        DocumentEntity documentEntity = documentRepo.findById(signModel.getDocId())
                .orElseThrow(() -> new CDocIdIsNotExistException(ExceptionType.DOCUMENT_ID_IS_NOT_EXIST));

        long docId = signModel.getDocId();
        String userId = signModel.getUserId();
        int signNo = signModel.getSignNo();

        List<SignEntity> signEntityList = documentEntity.getDocSignEntityList();
        if(signEntityList != null && signEntityList.size() > 0){
            for(SignEntity signEntity : signEntityList){
                //해당 결재문서, 결재자, 순번이 동일한지 확인
                if(signEntity.getDocument().getId() == docId && userId.equals(signEntity.getUser().getId()) && signEntity.getSignNo() == signNo){
                    throw new CSignDataIsExistException(ExceptionType.SIGN_DATA_IS_EXIST);
                }

                // 해당 결재문서에 등록 된 결재자인지 확인
                if(signEntity.getDocument().getId() == docId && userId.equals(signEntity.getUser().getId())){
                    throw new CSignUserExistException(ExceptionType.SIGN_USER_EXIST);
                }

                // 해당 결재문서의 순번이 등록되었는지 확인
                if(signEntity.getDocument().getId() == docId && signEntity.getSignNo() == signNo ){
                    throw new CSignNoExistException(ExceptionType.SIGN_NO_EXIST);
                }
            }
        }else{
            documentEntity.setDocStatus("PROGRESS");
        }
        signModel.setSignStatus("PROGRESS");
        SignEntity signEntity = SignEntity.create(documentEntity, userEntity, new SignId(docId,userId),signModel);
        return SignModel.of(signRepo.save(signEntity));
    }

    /**
     * 결재 문서 처리
     * @param signModel
     */
    @Override
    public void updateSign(SignModel signModel) {
        //1. documentId / userId 체크
        UserEntity userEntity = userRepo.findById(signModel.getUserId())
                .orElseThrow(()-> new CUserIdIsNotExistException(ExceptionType.USER_ID_IS_NOT_EXIST));

        DocumentEntity documentEntity = documentRepo.findById(signModel.getDocId())
                .orElseThrow(() -> new CDocIdIsNotExistException(ExceptionType.DOCUMENT_ID_IS_NOT_EXIST));


        SignEntity signEntity = signRepo.findBySignId(new SignId(documentEntity.getId(),userEntity.getId()))
                .orElseThrow(() -> new CSignDataIsNotExistException(ExceptionType.SIGN_DATA_IS_NOT_EXIST));


        String userId = signModel.getUserId();
        String signStatus = signModel.getSignStatus();
        String opinion = signModel.getOpinion();


        String docStatus = documentEntity.getDocStatus();
        //2. 만료 문서(승인,거절)인지, 대기문서인지 체크
        if("APPROVAL".equals(docStatus) || "REFUSE".equals(docStatus)){
            throw new CDocumentExpiredException(ExceptionType.DOCUMENT_EXPIRED);
        }else if("READY".equals(docStatus)){
            //결재대기 상태 문서. 결재문서 등록자가 결재자를 등록하도록 유도.
            throw new CSignUserNotExistException(ExceptionType.SIGN_USER_NOT_EXIST);
        }

        List<SignEntity> signEntityList = documentEntity.getDocSignEntityList().stream().filter(s -> "PROGRESS".equals(s.getSignStatus()))
                                                                                        .sorted(Comparator.comparing((SignEntity s) -> s.getSignNo()))
                                                                                        .collect(Collectors.toList());

        //3. 해당 문서의 결재자가 맞는지 체크
        boolean signUserMatch =  signEntityList.stream().anyMatch(s -> userId.equals(s.getUser().getId()));
        if(!signUserMatch){
            throw new CNotSignUserException(ExceptionType.NOT_SIGN_USER);
        }

        //4. user의 결재 순번이 맞는지 체크
        if(!userId.equals(signEntityList.get(0).getUser().getId())){
            throw new CSignNoIsNotUserTurnException(ExceptionType.SIGN_NO_IS_NOT_USER_TURN);
        }

        //5. 결재처리
        if(signStatus.equals("APPROVAL") && signEntityList.size() == 1){
            //결재자가 문서 승인을 할때, 남은 문서 결재자가 한명이라면 결재문서를 승인처리로 바꾼다.
            documentEntity.setDocStatus("APPROVAL");
        }else if(signStatus.equals("REFUSE")){
            //결재자가 결재거절을 하면, 결재문서를 결재거절처리로 바꾼다.
            documentEntity.setDocStatus("REFUSE");
        }
        signEntity.setSignStatus(signStatus);
        signEntity.setOpinion(opinion);
        signRepo.save(signEntity);

    }

}
