package com.hk.logging.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User body) {
        User user = new User();
        user.setUsername(body.username);
        user.setPassword(body.password);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.save(user));
    }
}
