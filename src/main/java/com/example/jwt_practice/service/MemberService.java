package com.example.jwt_practice.service;

import com.example.jwt_practice.entity.Member;
import com.example.jwt_practice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void joinMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

//    public String loginMember(LoginRequestDto loginRequestDto) {
//        String memberEmail = loginRequestDto.getEmail();
//
////        Authentication authentication = authenticationManager
////                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
//
//        // SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println("생성한 JWT" + JwtUtil.createJwt(loginRequestDto.getEmail()));
//        return JwtUtil.createJwt(memberEmail);
//    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("not found loginId: " + email));
    }

}
