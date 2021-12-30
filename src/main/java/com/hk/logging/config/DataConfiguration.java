package com.hk.logging.config;

import com.hk.logging.user.User;
import com.hk.logging.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@AllArgsConstructor
@Configuration
public class DataConfiguration implements CommandLineRunner {

    private final UserRepository userRepository;


    public void run(String... args) {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User(1L,"John", "Doe");
            User user2 = new User(2L,"John", "Cook");
            userRepository.save(user1);
            userRepository.save(user2);
        }
        log.info("Users count {}", userRepository.count());
    }
}
