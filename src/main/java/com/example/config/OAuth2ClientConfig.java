package com.example.config;

import com.example.oauth.handler.CustomSuccessHandler;
import com.example.oauth.mapper.CustomAuthorityMapper;
import com.example.oauth.service.CustomOAuth2UserService;
import com.example.oauth.service.CustomOidcUserService;
import com.example.repository.MemberRepository;
import com.example.token.JwtFilter;
import com.example.token.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MemberRepository memberRepository;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/js/**" ,
                "/static/images/**" , "/static/css/**","/static/scss/**");
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper() {
        return new CustomAuthorityMapper();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3001")); // 클라이언트의 도메인 설정
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드를 허용
                        configuration.setAllowCredentials(true); // 자격 증명을 허용
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더를 허용
                        configuration.setMaxAge(3600L); // 캐시 지속 시간 설정

                        // 여기서 두 개의 헤더를 노출하도록 설정
                        configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization")); // 노출할 헤더 설정

                        return configuration;
                    }
                }));
        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        http.authorizeHttpRequests(authRequest -> authRequest.
                requestMatchers(new AntPathRequestMatcher("/api/user")).hasAnyRole("OAUTH2_USER").
                requestMatchers(new AntPathRequestMatcher("/api/oidc")).hasAnyRole("SCOPE_openid").
                requestMatchers(new AntPathRequestMatcher("/**")).permitAll().
                requestMatchers(new AntPathRequestMatcher("/login")).permitAll());

        http
                .addFilterBefore(new JwtFilter(jwtService,memberRepository), OAuth2LoginAuthenticationFilter.class);

        http.oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(
                userInfo -> userInfo
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService))
                        .successHandler(customSuccessHandler));
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}
