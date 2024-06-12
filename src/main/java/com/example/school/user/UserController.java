package com.example.school.user;


import com.example.school.rank.RankDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 유저 전체 가져오기
    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 유저 저장
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user) { // @RequestBody로 data 받아옴
        ResponseEntity<String> CONFLICT = userService.userIdCheck(user); // 중복 확인
        if (CONFLICT != null) return CONFLICT; // Null 경우 return
        User savedUser = userService.save(user); // 저장

        return ResponseEntity.ok(savedUser);
    }

    // 로그인
    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.login(user);
    }

    // 전체 랭킹
    @GetMapping("/rank/all")
    public List<RankDTO> userRankTopFive() {
        return userService.getUserRankingTopFive();
    }

    // 내 랭킹
    @GetMapping("/rank/user")
    public Integer usesRank(@RequestParam("userID") String userID) {
        return userService.getUserRank(userID);
    }

    // 점수 더하기
    @PostMapping("/rank/add-point")
    public String userRankUp(@RequestParam("userID") String userID) {
        log.info("UserController.userRankUp: userID={}", userID);
        return userService.addRankPoint(userID);
    }

    // 점수 빼기
    @PostMapping("/rank/sub-point")
    public String userRankDown(@RequestParam("userID") String userID) {
        log.info("UserController.userRankDown: userID={}", userID);
        return userService.subRankPoint(userID);
    }
}
