package com.example.token;

import com.example.domain.Member;
import com.example.oauth.token.CustomAuthenticationToken;
import com.example.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * JWT 를 사용할 때
 */
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresentOrElse(
                        this::saveAuthentication,
                        () -> checkRefreshToken(request,response)
                );
        filterChain.doFilter(request, response);
    }

    /**
     * accessToken 유효 -> authentication 저장
     * accessToken 만료
     *      refreshToken 유효 -> authentication 저장, accessToken 갱신
     *      refreshToken 만료 -> authentication 저장 X
     */


    private void saveAuthentication(String accessToken) {
        String email = jwtService.extractMemberEmail(accessToken);

        Member member = memberRepository.findByEmail(email);

        CustomAuthenticationToken token = new CustomAuthenticationToken(member, null);
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private void checkRefreshToken(HttpServletRequest request , HttpServletResponse response) {
        Optional<String> refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid);

        if (refreshToken.isPresent()) {
            // RefreshToken 은 잠시 넘기기

        } else {
            doNotSaveAuthentication(request , response);
        }
    }

    private void doNotSaveAuthentication(HttpServletRequest request , HttpServletResponse response)  {

    }
}
