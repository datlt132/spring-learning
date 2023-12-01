package com.learning.service;

import com.learning.base.dto.ServerResponse;
import com.learning.entity.UserEntity;
import com.learning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> list() {
        return userRepository.findAll();
    }

    public ServerResponse save(UserEntity user) {
        return ServerResponse.successWith(userRepository.save(user));
    }


}
