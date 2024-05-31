package com.example.school.userTest;

import com.example.school.user.User;
import com.example.school.user.UserRepository;
import com.example.school.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void userTest() {
        User user = new User();
        user.setId(1L);
        user.setUserID("test");
        user.setPassword("1234");
        user.setPhoneNumber("010-1234-1234");
        user.setAddress("대구 중구");
        user.setCleaner(false);


        Optional<User> testUser = userRepository.findByUserIDAndPassword(user.getUserID(), user.getPassword());
        if (testUser.isPresent()) {
            System.out.println("find User!");
        }
    }


}
