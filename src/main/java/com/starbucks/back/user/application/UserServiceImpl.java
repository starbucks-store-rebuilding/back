package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSignUpDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signUp(RequestSignUpDto requestSignUpDto) {
        userRepository.save(requestSignUpDto.toEntity(passwordEncoder));
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
