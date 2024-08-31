package com.example.controller.oauth;

import com.example.oauth.token.CustomAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

//    @GetMapping("/")
//    public String index(Model model , Authentication authentication ,
//                        @AuthenticationPrincipal OAuth2User oAuth2User) {
//
//        CustomAuthenticationToken oAuth2AuthenticationToken = (CustomAuthenticationToken) authentication;
//
//        Object principal = oAuth2AuthenticationToken.getPrincipal();
//        log.info("principal = {} " , principal.getClass());
//        log.info("name = {} " , oAuth2AuthenticationToken.getName());
//
//
//        return "index";
//    }
}
