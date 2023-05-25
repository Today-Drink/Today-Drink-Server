package com.example.todaydrinkserver.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
   private MockMvc mvc;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {
        // given
        RequestSignup user = RequestSignup.builder()
                .userId("chae01")
                .userPwd("1234")
                .userName("채원")
                .build();
        String jsonContent = objectMapper.writeValueAsString(user);
        // when


        // then
        mvc.perform(post("/api/users/join")
                .contentType(MediaType.APPLICATION_JSON) // 보내는 데이터의 타입
                .content(jsonContent) // HTTP body에 담은 데이터
                );
                //.andExpect(MockMvcResultMatchers.status());

    }
}