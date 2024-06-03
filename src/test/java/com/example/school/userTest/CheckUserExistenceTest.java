package com.example.school.userTest;

import com.example.school.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
public class CheckUserExistenceTest {

    @Autowired
    UserService userService;


    @Test
    void checkUser() {
        boolean test1 = userService.checkUserExistence("test1");
//        assertThat(test1).isTrue();
        assertThat(test1).isFalse();
    }
}
