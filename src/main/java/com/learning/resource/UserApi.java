package com.learning.resource;

import com.learning.base.dto.ServerResponse;
import com.learning.entity.UserEntity;
import com.learning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok(userService.list());
    }

    @PostMapping
    public ResponseEntity<ServerResponse> save(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.save(user));
    }
}
