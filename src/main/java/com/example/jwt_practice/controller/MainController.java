package com.example.jwt_practice.controller;

import com.example.jwt_practice.dto.LoginRequestDto;
import com.example.jwt_practice.dto.LoginResponseDto;
import com.example.jwt_practice.entity.Member;
import com.example.jwt_practice.service.MemberService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @PostMapping("/join")
    public String join(@RequestBody Member Member) {
        memberService.joinMember(Member);
        return "join";
    }

}
