package com.style.approval.web.controller;

import com.style.approval.config.EnableMockMvc;
import com.style.approval.web.model.UserModel;
import com.style.approval.web.service.user.UserService;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.style.approval.ApiDocumentUtil.getDocumentRequest;
import static com.style.approval.ApiDocumentUtil.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@EnableMockMvc
@AutoConfigureRestDocs
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;


    @BeforeEach
    public void setUp() throws Exception{
        UserModel userModel = UserModel.builder().userId("No6").password("1234").build();
        userService.saveUser(userModel);
    }

    @Test
    @DisplayName("유저 - 로그인")
    void loginUser() throws Exception{
        // given
        // id:No1 , password : 1234 가 가입한 상태

        // when
        // id가 'No1'인 유저가 로그인하는 경우
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/api/userLogin")
                .characterEncoding("UTF-8")
                .header("Content-Type","application/json")
                .content("{\"userId\":\"" + "No1" + "\",\"password\":\"" + "1234" + "\"}")).andDo(print());

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(document(
                        "loginUser",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.STRING).description("사용자 ID"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공유무"),
                                fieldWithPath("error").type(JsonFieldType.NULL).description("에러내용"),
                                fieldWithPath("response.userId").type(JsonFieldType.STRING).description("사용자 ID"),
                                fieldWithPath("response.password").type(JsonFieldType.STRING).description("비밀번호")
                        )
                ));

    }


}