package com.example.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/api/user")
    public String user(Authentication authentication , @AuthenticationPrincipal OAuth2User oAuth2User) {
        log.info (" authentication = {} , oAuth2User = {} " , authentication , oAuth2User);
        return "authentication";
    }


}
