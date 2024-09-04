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
import java.util.List;

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


        //CORS 설정
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        System.out.println("OAuth2ClientConfig.getCorsConfiguration");

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3001"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-" +
                                "Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        // configuration.setAllowedOrigins(List.of("Access-Control-Allow-Origin"));

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
