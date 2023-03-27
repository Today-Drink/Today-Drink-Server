package com.example.todaydrinkserver.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginTestController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    User user = User.builder()
            .userId("abc@gmail.com")
            .userPwd("1234!")
            .userName("김하마")
            .roles(Collections.singletonList("ROLE_USER"))
            .build();

    @PostMapping("/join")
    public String join(){
        log.info("로그인 시도됨");

        userRepository.save(user);


        return user.toString();

    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        log.info("user id = {}", userDto.getUserId());
        User member = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
