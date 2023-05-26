package com.example.todaydrinkserver.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestLogin {
    private String userId;
    private String userPw;

    public RequestLogin(){

    }
    public RequestLogin(String userId, String userPw){
        this.userId = userId;
        this.userPw = userPw;
    }
}
