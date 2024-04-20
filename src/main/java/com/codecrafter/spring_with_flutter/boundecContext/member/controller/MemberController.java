package com.codecrafter.spring_with_flutter.boundecContext.member.controller;

import com.codecrafter.spring_with_flutter.base.rs.ResponseDataWrapper;
import com.codecrafter.spring_with_flutter.boundecContext.member.dto.RegisterDto;
import com.codecrafter.spring_with_flutter.boundecContext.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDataWrapper> register(@RequestBody @Valid RegisterDto registerDto) {
        return ResponseEntity.ok(memberService.userPasswordRegister(registerDto));
    }
}
