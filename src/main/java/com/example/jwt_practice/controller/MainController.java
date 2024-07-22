package com.example.jwt_practice.controller;

import com.example.jwt_practice.entity.Member;
import com.example.jwt_practice.service.MemberService;
import com.example.jwt_practice.service.RefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MainController {

    @Autowired
    MemberService memberService;

    @Autowired
    RefreshTokenService refreshTokenService;

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

    @PostMapping("/refresh")
    public String refresh(@RequestHeader("Authorization") String refreshToken) {
        refreshTokenService.findRefreshToken(refreshToken);
        return "refresh";
    }

}
