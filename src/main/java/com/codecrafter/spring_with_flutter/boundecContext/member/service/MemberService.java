package com.codecrafter.spring_with_flutter.boundecContext.member.service;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.member.dto.RegisterDto;
import com.codecrafter.spring_with_flutter.boundecContext.member.entity.Member;
import com.codecrafter.spring_with_flutter.boundecContext.member.repository.MemberRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
}
