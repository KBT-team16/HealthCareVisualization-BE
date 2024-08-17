package com.example.controller;

import com.example.controller.response.InbodyResponseDto;
import com.example.service.InbodyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.controller.response.InbodyResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InbodyController {

    private final InbodyService inbodyService;

    @GetMapping("/inbody/sample")
    public ResponseEntity<SampleData> getSample() {
        return ResponseEntity.ok().body(inbodyService.getSampleData());

    }

    @GetMapping("/inbody/analyze")
    public ResponseEntity<Analyze> analyze() {
        return ResponseEntity.ok().body(inbodyService.analyze());
    }


}
