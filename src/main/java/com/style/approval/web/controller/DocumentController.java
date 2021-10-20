package com.style.approval.web.controller;

import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.model.common.response.ListResult;
import com.style.approval.web.model.common.response.SingleResult;
import com.style.approval.web.service.document.DocumentService;
import com.style.approval.web.service.response.ResponseService;
import com.style.approval.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = "application/json; charset=utf8")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocumentController {
    private final ResponseService responseService;
    private final DocumentService documentService;

    /**
     * 문서 생성
     * @param documentModel
     * @return
     */
    @PostMapping("/createDocument")
    public SingleResult createDocument(@RequestBody DocumentModel documentModel){
        DocumentModel resultModel = documentService.saveDocument(documentModel);
        return responseService.getSingleResult(resultModel);
    }


    /**
     * 유저가 등록한 결재 진행 중인 문서 조회
     * @param documentModel
     * @return
     */
    @PostMapping("/getUserOutbox")
    public ListResult<DocumentModel> getUserOutbox(@RequestBody DocumentModel documentModel){
        return responseService.getListResult(documentService.getUserOutboxList(documentModel));
    }

    /**
     * 유저가 결재해야할 문서 조회
     * @param userModel
     * @return
     */
    @PostMapping("/getUserInbox")
    public ListResult<DocumentModel> getUserInbox(@RequestBody UserModel userModel){
        return responseService.getListResult(documentService.getUserInboxList(userModel));
    }

    /**
     * 유저가 관여한 문서 중 결재가 완료 된 문서(archive)
     * @param userModel
     * @return
     */
    @PostMapping("/getUserArchive")
    public ListResult<DocumentModel> getUserArchive(@RequestBody UserModel userModel){
        return responseService.getListResult(documentService.getUserArchive(userModel));
    }

    /**
     * 문서 보기
     * @param documentModel
     * @return
     */
    @PostMapping("/getDocument")
    public SingleResult getDocument(@RequestBody DocumentModel documentModel){
        DocumentModel resultModel = documentService.selectDocument(documentModel);
        return responseService.getSingleResult(resultModel);
    }






}
