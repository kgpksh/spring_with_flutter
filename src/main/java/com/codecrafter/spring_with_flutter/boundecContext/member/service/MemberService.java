package com.codecrafter.spring_with_flutter.boundecContext.member.service;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.base.security.jwt.JwtProvider;
import com.codecrafter.spring_with_flutter.boundecContext.member.dto.LoginDto;
import com.codecrafter.spring_with_flutter.boundecContext.member.dto.RegisterDto;
import com.codecrafter.spring_with_flutter.boundecContext.member.entity.Member;
import com.codecrafter.spring_with_flutter.boundecContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public ResponseDataWrapper userPasswordRegister(RegisterDto registerDto) {
        String validation = validatePasswordRegister(registerDto);
        if(validation != null) {
            return ResponseDataWrapper.of("F-0", validation, null);
        }

        Member member = new Member();
        member.setUsername(registerDto.getUsername());
        member.setNickname(registerDto.getNickname());
        member.setAdmin(false);
        member.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        member.setProviderTypeCode("password");
        memberRepository.save(member);
        return ResponseDataWrapper.of("S-0", null, null);
    }

    private String validatePasswordRegister(RegisterDto registerDto) {
        Optional<Member> member = memberRepository.findMemberByUsername(registerDto.getUsername());
        if(member.isPresent()) {
            return "이미 존재하는 유저입니다";
        }

        return null;
    }

    public ResponseDataWrapper userPasswordLogin(LoginDto loginDto) {
        Optional<Member> wrappedMember = memberRepository.findMemberByUsername(loginDto.getUsername());
        String validation = validatePasswordLogin(wrappedMember, loginDto.getPassword());
        if(validation != null) {
            return ResponseDataWrapper.of("F-0", validation, null);
        }
        Member member = wrappedMember.get();

        return ResponseDataWrapper.of("S-1", "로그인 성공",
                jwtProvider.createJwt(member.getUsername()));
    }

    private String validatePasswordLogin(Optional<Member> wrappedMember, String password) {
        if(wrappedMember.isEmpty()) {
            return "존재하지 않는 아이디 입니다";
        }

        Member member = wrappedMember.get();
        if (!passwordEncoder.matches(password,member.getPassword())){
            return "잘못된 비밀번호 입니다";
        }

        return null;
    }
}
