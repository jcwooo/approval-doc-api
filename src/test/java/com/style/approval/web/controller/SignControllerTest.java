package com.style.approval.web.controller;

import com.style.approval.config.EnableMockMvc;
import com.style.approval.web.model.DocumentModel;
import com.style.approval.web.model.SignModel;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.service.document.DocumentService;
import com.style.approval.web.service.sign.SignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static com.style.approval.ApiDocumentUtil.getDocumentRequest;
import static com.style.approval.ApiDocumentUtil.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@EnableMockMvc
@AutoConfigureRestDocs
class SignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SignService signService;

    /**
     * 결재자 등록
     * @throws Exception
     */
    @Test
    @DisplayName("결재 - 결재자 생성")
    void createSignUser() throws Exception{
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/createSignUser")
                .header("Content-Type", "application/json")
                .content("{\"docId\":1, \"userId\":\"No1\", \"signNo\":1}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "addSignUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("docId").type(JsonFieldType.NUMBER).description("결재 문서 ID"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("결재자 ID"),
                                fieldWithPath("signNo").type(JsonFieldType.NUMBER).description("결재 순번")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response.docId").type(JsonFieldType.NUMBER).description("결재 문서 번호(SEQUENCE)"),
                                fieldWithPath("response.userId").type(JsonFieldType.STRING).description("결재자 ID"),
                                fieldWithPath("response.signNo").type(JsonFieldType.NUMBER).description("결재 순번"),
                                fieldWithPath("response.signStatus").type(JsonFieldType.STRING).description("결재 상태"),
                                fieldWithPath("response.opinion").type(JsonFieldType.NULL).description("의견"),
                                fieldWithPath("response.createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response.updatedAt").type(JsonFieldType.STRING).description("수정일")
                        )
                ));
    }

    /**
     * 결재 처리
     * @throws Exception
     */
    @Test
    @DisplayName("결재 - 결재 처리")
    void updateSign() throws Exception{
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .patch("/api/processSign")
                .header("Content-Type", "application/json")
                .content("{\"docId\":1, \"userId\":\"No3\", \"signStatus\":\"APPROVAL\",\"opinion\":\"승인하겠습니다.\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "updateSign",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("docId").type(JsonFieldType.NUMBER).description("결재 문서 ID"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("결재자 ID"),
                                fieldWithPath("signStatus").type(JsonFieldType.STRING).description("결재 상태"),
                                fieldWithPath("opinion").type(JsonFieldType.STRING).description("의견")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response").type(JsonFieldType.BOOLEAN).description("response")
                        )
                ));
    }
}