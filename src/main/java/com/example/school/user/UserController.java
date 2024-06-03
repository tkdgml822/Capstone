package com.example.school.user;


import com.example.school.rank.RankDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    public ResponseEntity<?> save(@RequestBody User user) { // @RequestBody로 data 받아옴
        ResponseEntity<String> CONFLICT = userIdCheck(user);
        if (CONFLICT != null) return CONFLICT;
        User savedUser = userService.save(user);

        return ResponseEntity.ok(savedUser);
    }

    private ResponseEntity<String> userIdCheck(User user) {
        if (!userService.checkUserExistence(user.getUserID())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("아이디가 이미 사용 중입니다.");
        }
        return null;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.login(user);
    }

    // 전체 랭킹
    @GetMapping("/rank/all")
    public List<RankDTO> userRankTopFive() {
        return userService.getUserRankingTopFive();
    }

    @GetMapping("/rank/user")
    public Integer usesRank(@RequestParam("userID") String userID) {
        return userService.getUserRank(userID);
    }
    
}
