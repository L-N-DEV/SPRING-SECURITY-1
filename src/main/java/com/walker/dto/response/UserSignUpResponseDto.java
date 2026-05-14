package com.walker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignUpResponseDto {

    private Long userId;

    private String fullName;

    private String username;

    private String email;

    private String role;

    private Boolean enabled;

    private LocalDateTime createdAt;
}
