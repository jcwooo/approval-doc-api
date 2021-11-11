package com.style.approval.web.controller;

import com.style.approval.config.EnableMockMvc;
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
class DocumentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("문서 - 생성")
    void createDocument() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/createDocument")
                .header("Content-Type", "application/json")
                .content("{\"title\":\"긴급 결재\", \"type\":\"긴급\", \"contents\":\"긴급합니다.\", \"writerId\":\"No5\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "addDocument",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("type").type(JsonFieldType.STRING).description("문서 분류"),
                                fieldWithPath("contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("writerId").type(JsonFieldType.STRING).description("작성자 ID")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response.docId").type(JsonFieldType.NUMBER).description("문서 번호(SEQUENCE)"),
                                fieldWithPath("response.title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("response.type").type(JsonFieldType.STRING).description("문서 타입"),
                                fieldWithPath("response.contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("response.writerId").type(JsonFieldType.STRING).description("작성자 ID"),
                                fieldWithPath("response.docStatus").type(JsonFieldType.STRING).description("문서 상태"),
                                fieldWithPath("response.createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response.updatedAt").type(JsonFieldType.STRING).description("수정일")
                        )
                ));
    }

    @Test
    @DisplayName("문서 - OUTBOX(생성한 문서 중 결재 진행중인 문서) 조회")
    void getUserOutboxList() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/getUserOutbox")
                .header("Content-Type", "application/json")
                .content("{\"writerId\":\"No5\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "getUserOutbox",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("writerId").type(JsonFieldType.STRING).description("유저ID")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response[].docId").type(JsonFieldType.NUMBER).description("문서 번호(SEQUENCE)"),
                                fieldWithPath("response[].title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("response[].type").type(JsonFieldType.STRING).description("문서 타입"),
                                fieldWithPath("response[].contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("response[].writerId").type(JsonFieldType.STRING).description("작성자 ID"),
                                fieldWithPath("response[].docStatus").type(JsonFieldType.STRING).description("문서 상태"),
                                fieldWithPath("response[].createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response[].updatedAt").type(JsonFieldType.NULL).description("수정일")
                        )
                ));
    }

    @Test
    @DisplayName("문서 - InBOX(결재 해야할 문서) 조회")
    void getUserInboxList() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/getUserInbox")
                .header("Content-Type", "application/json")
                .content("{\"userId\":\"No3\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "getUserInbox",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("유저ID")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response[].docId").type(JsonFieldType.NUMBER).description("문서 번호(SEQUENCE)"),
                                fieldWithPath("response[].title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("response[].type").type(JsonFieldType.STRING).description("문서 타입"),
                                fieldWithPath("response[].contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("response[].writerId").type(JsonFieldType.STRING).description("작성자 ID"),
                                fieldWithPath("response[].docStatus").type(JsonFieldType.STRING).description("문서 상태"),
                                fieldWithPath("response[].createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response[].updatedAt").type(JsonFieldType.NULL).description("수정일")
                        )
                ));
    }

    @Test
    @DisplayName("문서 - 결재 문서 조회")
    void getDocument() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/getDocument")
                .header("Content-Type", "application/json")
                .content("{\"docId\":1}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "getDocument",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("docId").type(JsonFieldType.NUMBER).description("문서번호")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response.docId").type(JsonFieldType.NUMBER).description("문서 번호(SEQUENCE)"),
                                fieldWithPath("response.title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("response.type").type(JsonFieldType.STRING).description("문서 타입"),
                                fieldWithPath("response.contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("response.writerId").type(JsonFieldType.STRING).description("작성자 ID"),
                                fieldWithPath("response.docStatus").type(JsonFieldType.STRING).description("문서 상태"),
                                fieldWithPath("response.createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response.updatedAt").type(JsonFieldType.NULL).description("수정일")
                        )
                ));
    }

    @Test
    @DisplayName("문서 - ARCHIVE(관여한 문서 중 완료된 문서) 문서 조회")
    void getUserArchive() throws Exception {
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/getUserArchive")
                .header("Content-Type", "application/json")
                .content("{\"userId\":\"No3\"}"))
                .andDo(MockMvcResultHandlers.print());

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "getUserArchive",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("유저ID")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response[].docId").type(JsonFieldType.NUMBER).description("문서 번호(SEQUENCE)"),
                                fieldWithPath("response[].title").type(JsonFieldType.STRING).description("문서 제목"),
                                fieldWithPath("response[].type").type(JsonFieldType.STRING).description("문서 타입"),
                                fieldWithPath("response[].contents").type(JsonFieldType.STRING).description("문서 내용"),
                                fieldWithPath("response[].writerId").type(JsonFieldType.STRING).description("작성자 ID"),
                                fieldWithPath("response[].docStatus").type(JsonFieldType.STRING).description("문서 상태"),
                                fieldWithPath("response[].createdAt").type(JsonFieldType.STRING).description("생성일"),
                                fieldWithPath("response[].updatedAt").type(JsonFieldType.NULL).description("수정일")
                        )
                ));
    }



}