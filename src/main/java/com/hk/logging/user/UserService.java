package com.hk.logging.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(long id) {
        return userRepository.getById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
