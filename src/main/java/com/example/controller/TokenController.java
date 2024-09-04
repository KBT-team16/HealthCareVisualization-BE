package com.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class TokenController {

    @GetMapping("/token")
    public String cookieToHeader(HttpServletRequest request, HttpServletResponse response) {

        log.info("로그인 성공 후 메인페이지로 오게 되면 쿠키에 있던 값을 헤더로 옮기는 작업이 시작된다.");

        Cookie[] cookies = request.getCookies();
        String result = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 쿠키 이름이 "Authorization"인 쿠키를 찾음
                if ("Authorization".equals(cookie.getName())) {
                    String authToken = cookie.getValue();

                    log.info("authToken = {}" , authToken);

                    // Authorization 쿠키 값을 응답 헤더에 설정
                    // response.setHeader("Access-Control-Allow-Origin","*");
                    response.setHeader("authorization", authToken);

                    // 클라이언트로 간단한 메시지를 반환
                    result = "Authorization cookie found and set in response header";
                }
            }
        }

        // Authorization 쿠키가 없는 경우
        result = "No Authorization cookie found";
        return result;
    }

}
