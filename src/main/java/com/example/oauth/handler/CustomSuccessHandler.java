package com.example.oauth.handler;

import com.example.oauth.token.CustomAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //OAuth2User
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        CustomAuthenticationToken customToken = new CustomAuthenticationToken(oAuth2User, null,oAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(customToken);
        response.sendRedirect("http://localhost:3001/");
    }
}
