package com.walker.security;

import com.walker.dto.request.UserLoginRequestDto;
import com.walker.dto.response.UserLoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto login(
            UserLoginRequestDto userLoginRequestDto,
            HttpServletRequest httpServletRequest
    ) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginRequestDto.getUsernameOrEmail(),
                        userLoginRequestDto.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        SecurityContext securityContext =
                SecurityContextHolder.createEmptyContext();

        securityContext.setAuthentication(authentication);

        SecurityContextHolder.setContext(securityContext);

        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setMaxInactiveInterval(3600);

        httpSession.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                securityContext
        );

        CustomUserDetails customUserDetails =
                (CustomUserDetails) authentication.getPrincipal();

        String role = customUserDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        return UserLoginResponseDto.builder()
                .userId(customUserDetails.getId())
                .fullName(customUserDetails.getFullName())
                .username(customUserDetails.getUsername())
                .email(customUserDetails.getEmail())
                .role(role)
                .accessToken(null)
                .tokenType("SESSION")
                .build();
    }
}
