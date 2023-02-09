package com.springboot.pjt1.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.openid.connect.sdk.UserInfoRequest;
import com.springboot.pjt1.data.dto.MemberDTO;
import com.springboot.pjt1.data.dto.UserRequestMapper;
import com.springboot.pjt1.data.entity.Token;
import com.springboot.pjt1.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("OAuth2SuccessHandler - onAuthenticationSuccess begin");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        MemberDTO memberDTO = userRequestMapper.toDto(oAuth2User);

        log.info("꺼낸 OAuth2User = {}", oAuth2User);
        // 최초 로그인이라면 회원가입 처리를 한다
        String targetUrl;
        log.info("토큰 발행 시작");

        Token token = tokenService.generateToken(memberDTO.getEmail(), "USER");
        log.info("{}", token);
        targetUrl = UriComponentsBuilder.fromUriString("/home")
                .queryParam("token", token)
                .build().toUriString();
        System.out.println(targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

        System.out.println("OAuth2SuccessHandler - onAuthenticationSuccess end");
    }
}
