package com.example.school.user;


import com.example.school.rank.RankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) { // @RequestBody로 data 받아옴
        return userService.save(user);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("rank/all")
    public List<RankDTO> userRankTopFive() {
        return userService.getTopFiveUserRanking();
    }
}
