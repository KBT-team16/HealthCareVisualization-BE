package com.example.oauth.handler;

import com.example.controller.response.MemberResponseDto;
import com.example.domain.Member;
import com.example.oauth.token.CustomAuthenticationToken;
import com.example.repository.MemberRepository;
import com.example.token.JwtService;
import com.example.token.TokenMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

import static com.example.controller.response.MemberResponseDto.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private ObjectMapper om = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Kakao 에서 Email 을 받기 위해서는 심사가 필요하기 때문에 "NAME UUID" 형태로 저장
        // 중간에 공백으로 구분

        String email = (String) oAuth2User.getAttribute("email");
        Member member = memberRepository.findByEmail(email);

        if (member != null) {
            CustomAuthenticationToken customToken = new CustomAuthenticationToken(oAuth2User, null,oAuth2User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(customToken);

            TokenMapping token = jwtService.createToken(email);

            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);


            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            // 액세스 토큰을 Authorization 헤더에 추가

            // Refresh 토큰을 쿠키에 추가
            response.addCookie(createCookie("AuthorizationAccess", token.getAccessToken()));
            response.addCookie(createCookie("AuthorizationRefresh", token.getRefreshToken()));

            // 클라이언트를 mainPage로 리다이렉트
            response.sendRedirect("http://localhost:3001");
        } else {
            response.sendRedirect("http://localhost:3001");
        }

    }

    // refreshToken 을 보낼때는 Cookie 사용
    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }
}
