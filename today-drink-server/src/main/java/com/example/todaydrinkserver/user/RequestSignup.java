package com.example.todaydrinkserver.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class RequestSignup {
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String userName;
    @NotBlank(message="아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-8]{4,20}$",message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String userId;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대수문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String userPw;

    public RequestSignup(){

    }


}
