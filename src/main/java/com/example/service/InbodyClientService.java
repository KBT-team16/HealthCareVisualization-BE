package com.example.service;

import com.example.controller.request.InbodyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inbody-client" , url = "http://localhost:8080")
public interface InbodyClientService {
    @PostMapping("/match")
    void transmitMemberData(@RequestBody InbodyDto inbodyDto);
}
