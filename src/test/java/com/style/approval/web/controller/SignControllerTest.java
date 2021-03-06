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
     * ????????? ??????
     * @throws Exception
     */
    @Test
    @DisplayName("?????? - ????????? ??????")
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
                                fieldWithPath("docId").type(JsonFieldType.NUMBER).description("?????? ?????? ID"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("????????? ID"),
                                fieldWithPath("signNo").type(JsonFieldType.NUMBER).description("?????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("????????????"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("????????????"),
                                fieldWithPath("response.docId").type(JsonFieldType.NUMBER).description("?????? ?????? ??????(SEQUENCE)"),
                                fieldWithPath("response.userId").type(JsonFieldType.STRING).description("????????? ID"),
                                fieldWithPath("response.signNo").type(JsonFieldType.NUMBER).description("?????? ??????"),
                                fieldWithPath("response.signStatus").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("response.opinion").type(JsonFieldType.NULL).description("??????"),
                                fieldWithPath("response.createdAt").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("response.updatedAt").type(JsonFieldType.STRING).description("?????????")
                        )
                ));
    }

    /**
     * ?????? ??????
     * @throws Exception
     */
    @Test
    @DisplayName("?????? - ?????? ??????")
    void updateSign() throws Exception{
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .patch("/api/processSign")
                .header("Content-Type", "application/json")
                .content("{\"docId\":1, \"userId\":\"No3\", \"signStatus\":\"APPROVAL\",\"opinion\":\"?????????????????????.\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "updateSign",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("docId").type(JsonFieldType.NUMBER).description("?????? ?????? ID"),
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("????????? ID"),
                                fieldWithPath("signStatus").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("opinion").type(JsonFieldType.STRING).description("??????")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("????????????"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("????????????"),
                                fieldWithPath("response").type(JsonFieldType.BOOLEAN).description("response")
                        )
                ));
    }
}