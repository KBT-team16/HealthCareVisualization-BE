package com.example.controller;

import com.example.controller.response.InbodyResponseDto;
import com.example.oauth.model.PrincipalUser;
import com.example.service.InbodyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.example.controller.response.InbodyResponseDto.*;

@RestController
@RequiredArgsConstructor
// @RequestMapping("/api/inbody")
public class InbodyController {

    private final InbodyService inbodyService;

    @GetMapping("/sample")
    public ResponseEntity<SampleData> getSample() {
        return ResponseEntity.ok().body(inbodyService.getSampleData());

    }

    @GetMapping("/analyze")
    public ResponseEntity<Analyze> analyze() {
        return ResponseEntity.ok().body(inbodyService.analyze());
    }

    @GetMapping("/history")
    public ResponseEntity<List<SingleInbodyData>> getHistories(@AuthenticationPrincipal PrincipalUser principalUser,
                                                               Pageable pageable) {
        return ResponseEntity.ok().body(inbodyService.getHistories(principalUser , pageable));
    }

    @GetMapping("/history/{history-id}")
    public ResponseEntity<SingleInbodyData> getHistories(@PathVariable("history-id") Long inbodyDataId) {
        return ResponseEntity.ok().body(inbodyService.getHistory(inbodyDataId));
    }
}
