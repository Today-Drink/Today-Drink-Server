package com.example.todaydrinkserver.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        // 유효한 토큰인지 확인합니다.
        if (accessToken != null && jwtTokenProvider.validateToken(accessToken)) {

            // ============로그아웃 처리 X================
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);


//            //==============Redis에 로그아웃 기록을 저장하여 처리할 때====================
//            // Redis에 해당 accessToken logout 여부를 확인
//            String isLogout = (String)redisTemplate.opsForValue().get(accessToken);
//
//            // 로그아웃이 없는(되어 있지 않은) 경우 해당 토큰은 정상적으로 작동
//            if(ObjectUtils.isEmpty(isLogout)){
//                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
//                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
//                // SecurityContext 에 Authentication 객체를 저장합니다.
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }

        }else{// accessToken이 만료되었을 경우

        }
        chain.doFilter(request, response);
    }
}
