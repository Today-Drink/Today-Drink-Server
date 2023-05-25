package com.example.todaydrinkserver.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseLogin {
    private String accessToken;
    private String refreshToken;

}
