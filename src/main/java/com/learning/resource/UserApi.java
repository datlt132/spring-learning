package com.learning.resource;

import com.learning.entity.UserEntity;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok(userService.list());
    }
}
