package com.example.config;

import com.example.oauth.mapper.CustomAuthorityMapper;
import com.example.oauth.service.CustomOAuth2UserService;
import com.example.oauth.service.CustomOidcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;

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
        http.authorizeHttpRequests(authRequest -> authRequest.
                requestMatchers(new AntPathRequestMatcher("/api/user")).hasAnyRole("OAUTH2_USER").
                requestMatchers(new AntPathRequestMatcher("/api/oidc")).hasAnyRole("SCOPE_openid").
                requestMatchers(new AntPathRequestMatcher("/")).permitAll().
                requestMatchers(new AntPathRequestMatcher("/login")).permitAll());

        http.oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(
                userInfo -> userInfo
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService)));

        //http.oauth2Login(oauth2Login -> oauth2Login.loginPage("/login"));


        http.logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}
