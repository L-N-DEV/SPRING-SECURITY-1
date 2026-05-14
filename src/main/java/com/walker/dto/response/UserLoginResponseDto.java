package com.walker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {

    private Long userId;

    private String fullName;

    private String username;

    private String email;

    private String role;

    private String accessToken;

    private String tokenType;
}
