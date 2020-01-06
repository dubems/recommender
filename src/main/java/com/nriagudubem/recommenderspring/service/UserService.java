package com.nriagudubem.recommenderspring.service;

import com.nriagudubem.recommenderspring.boundary.controller.dto.CreateUserRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.exceptions.UsernameAlreadyExistException;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public void createUser(CreateUserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            log.error("process=creatUser, status=failed, reason=username={} already exist", userRequestDto.getUsername());
            throw new UsernameAlreadyExistException("Username already exist");
        }
        userRepository.save(User.builder()
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword())).createdAt(Instant.now())
                .build());
    }


}
