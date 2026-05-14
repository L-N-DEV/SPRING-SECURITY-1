package com.walker.controller;

import com.walker.dto.request.UserLoginRequestDto;
import com.walker.dto.request.UserSignUpRequestDto;
import com.walker.dto.response.UserLoginResponseDto;
import com.walker.dto.response.UserSignUpResponseDto;
import com.walker.entity.User;
import com.walker.security.AuthService;
import com.walker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signup(@Valid @RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        UserSignUpResponseDto userSignUpResponseDto = userService.signup(userSignUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSignUpResponseDto);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> users = userService.getAllUsers();
       return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
            @Valid @RequestBody UserLoginRequestDto userLoginRequestDto,
            HttpServletRequest request
    ) {
        UserLoginResponseDto responseDto =
                authService.login(userLoginRequestDto, request);
        return ResponseEntity.ok(responseDto);
    }

}
