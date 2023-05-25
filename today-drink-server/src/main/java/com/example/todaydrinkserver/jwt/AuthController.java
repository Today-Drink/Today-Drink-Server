package com.example.todaydrinkserver.jwt;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/refresh")
    public  void validateRefreshToken(@RequestBody RefreshToken refreshToken){
        Map<String, String> map ;

    }

    @Data
    static class RefreshToken{
        private String refreshToken;
    }
}
