package com.youngandhun.moduleapi.auth.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youngandhun.moduleapi.auth.dto.response.LoginResp;
import com.youngandhun.moduleapi.auth.dto.request.RegisterReq;
import com.youngandhun.moduleapi.auth.exception.CustomSecurityException;
import com.youngandhun.moduleapi.auth.exception.SecurityErrorCode;
import com.youngandhun.moduleapi.auth.util.jwt.JwtProvider;
import com.youngandhun.modulecore.member.domain.Member;
import com.youngandhun.modulecore.member.exception.MemberErrorCode;
import com.youngandhun.modulecore.member.exception.MemberException;
import com.youngandhun.modulecore.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterReq request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Member newMember = Member.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword())) // 비밀번호 암호화
            .mobileNumber(request.getMobileNumber())
            .build();

        memberRepository.save(newMember);
    }

    public LoginResp login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        checkPassword(password, member);
        String accessToken = this.jwtProvider.issueJwtAccessToken(member.getMemberId(), member.getEmail(), member.getUsername());

        return LoginResp.builder()
            .username(member.getUsername())
            .email(member.getEmail())
            .accessToken(accessToken)
            .build();
    }

    private void checkPassword(String password, Member member) {
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomSecurityException(SecurityErrorCode.INVALID_PASSWORD);
        }
    }
}
