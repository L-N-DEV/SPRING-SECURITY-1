package com.walker.service;

import com.walker.dto.request.UserSignUpRequestDto;
import com.walker.dto.response.UserSignUpResponseDto;
import com.walker.entity.User;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface UserService {

    UserSignUpResponseDto signup(@Valid @NonNull UserSignUpRequestDto userSignUpRequestDto);
    List<User> getAllUsers();
}
