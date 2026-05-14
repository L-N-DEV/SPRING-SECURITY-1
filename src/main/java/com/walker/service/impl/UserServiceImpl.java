package com.walker.service.impl;

import com.walker.dto.request.UserSignUpRequestDto;
import com.walker.dto.response.UserSignUpResponseDto;
import com.walker.entity.User;
import com.walker.exception.UserNotFoundException;
import com.walker.mapper.UserMapper;
import com.walker.repository.UserRepository;
import com.walker.service.UserService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
     private final UserRepository userRepository;
     public UserServiceImpl(PasswordEncoder passwordEncoder , UserRepository userRepository) {
         this.passwordEncoder = passwordEncoder;
         this.userRepository = userRepository;
     }

    @Override
    public UserSignUpResponseDto signup(@Valid @NonNull UserSignUpRequestDto userSignUpRequestDto) {
         String password = userSignUpRequestDto.getPassword();
         String confirmPassword = userSignUpRequestDto.getConfirmPassword();
         if(!password.equals(confirmPassword)) {
             throw new IllegalArgumentException("Passwords do not match");
         }
         String encodedPassword =  passwordEncoder.encode(password);
         User user = UserMapper.toEntity(userSignUpRequestDto , encodedPassword);
         boolean isUserAllReadyExits = userRepository.existsByEmail(user.getEmail());
         if(isUserAllReadyExits) {
             throw new UserNotFoundException("User already exists");
         }
         User  user1 = userRepository.save(user);
        return UserMapper.toSignUpResponseDto(user1);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        for(int i = 0 ; i < users.size(); i++) {
            users.get(i).setPassword("PASSWORD NOT EXPOSED");
        }
        return users;
    }

}
