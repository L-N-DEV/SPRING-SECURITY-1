package com.walker.mapper;

import com.walker.dto.request.UserSignUpRequestDto;
import com.walker.dto.response.UserLoginResponseDto;
import com.walker.dto.response.UserSignUpResponseDto;
import com.walker.entity.User;
import com.walker.enums.Role;

public final class UserMapper {

    private UserMapper() {}

    public static User toEntity(UserSignUpRequestDto requestDto, String encodedPassword) {

        if (requestDto == null) {
            return null;
        }

        return User.builder()
                .fullName(requestDto.getFullName())
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    public static UserSignUpResponseDto toSignUpResponseDto(User user) {

        if (user == null) {
            return null;
        }

        return UserSignUpResponseDto.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .enabled(user.getEnabled())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static UserLoginResponseDto toLoginResponseDto(User user, String accessToken) {

        if (user == null) {
            return null;
        }

        return UserLoginResponseDto.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
