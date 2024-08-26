package com.example.controller;

import com.example.controller.request.InbodyDataDto;
import com.example.controller.response.MemberResponseDto;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.controller.response.MemberResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    // 모든 유저 조회
    @GetMapping("/members")
    public ResponseEntity<List<SingleMember>> getMembers() {
        return ResponseEntity.ok().body(memberService.getMembers());
    }

    // 데이터 입력
    @PostMapping("/member")
    public ResponseEntity<String> inputMemberInbodyData(@RequestBody InbodyDataDto inbodyDataDto) {
        return ResponseEntity.ok().body(memberService.saveInbodyData(inbodyDataDto));
    }
}
