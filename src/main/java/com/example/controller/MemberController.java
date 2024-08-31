package com.example.controller;

import com.example.controller.request.InbodyDataDto;
import com.example.controller.request.MemberRequestDto;
import com.example.controller.response.MemberResponseDto;
import com.example.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.controller.response.MemberResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
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

    //회원 정보 수정 (JWT 가 제대로 동작한다고 가정)
    @GetMapping("/member/info")
    public ResponseEntity<EditInfo> getMemberInfo(HttpServletRequest request) {
        log.info("@GetMapping(\"/member/info\") 호출");
        return ResponseEntity.ok().body(memberService.getMemberInfo(request));
    }

    @PatchMapping("/member/info")
    public ResponseEntity<String> editMemberInfo(@RequestBody MemberRequestDto.EditInfo editInfo ,
                                                   HttpServletRequest request) {
        log.info("@PatchMapping(\"/member/info\") 호출");
        return ResponseEntity.ok().body(memberService.editMemberInfo(editInfo , request));
    }

    @GetMapping("/member/inbody-history")
    public ResponseEntity<InbodyHistories> getInbodyHistory(HttpServletRequest request) {
        return ResponseEntity.ok().body(memberService.getInbodyHistories(request));
    }
}
