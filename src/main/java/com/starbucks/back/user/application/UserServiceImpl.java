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
        String encodedPassword = passwordEncoder.encode(requestSignUpDto.getPassword());

        userRepository.save(requestSignUpDto.toEntity(randomUUID().toString(), encodedPassword));
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean nicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
