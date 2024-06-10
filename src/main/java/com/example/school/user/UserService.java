package com.example.school.user;

import com.example.school.rank.RankDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public User save(User user) {
        if (user.getRankPoint() == null) {
            user.setRankPoint(0L);
        }

        return userRepository.save(user);
    }

    // 모든 유저 반환
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 유저 삭제
    public void delete(User user) {
        userRepository.delete(user);
    }

    // 유저 로그인
    public boolean login(User user) {
        log.info("userId={}", user.getUserID());

        Optional<User> findUser = userRepository.findByUserIDAndPassword(user.getUserID(), user.getPassword());
        return findUser.isPresent();
    }

    // 랭킹 탑 5
    public List<RankDTO> getUserRankingTopFive() {
        return userRepository.findAll().stream()
                .map(user -> new RankDTO(user.getUserID(), user.getRankPoint()))
                .sorted(Comparator.comparingLong(RankDTO::getRankPoint).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    // 내 랭킹 가져오기
    public int getUserRank(String username) {
        log.info("userID={}", username);
        User userId = userRepository.findByUserID(username);

        List<User> users = userRepository.findAll();
        //정렬
        List<User> sortedUsers = users.stream()
                .sorted(Comparator.comparingLong(User::getRankPoint).reversed())
                .toList();

        int resultRank = getResultRank(sortedUsers, userId);

        return resultRank + 1;
    }

    public String addRankPoint(String username) {
        log.info("usrID={}", username);
        User user = userRepository.findByUserID(username);

        if (user == null) {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        }
        Long rankPoint = user.getRankPoint();
        user.setRankPoint(rankPoint + 10);

        userRepository.save(user);

        return "OK";
    }

    // 정렬된 List에서 range을 써서 하나씩 증가 하다가 유저를 찾으면 range() 결과 값 반환
    private static int getResultRank(List<User> sortedUsers, User userId) {
        return IntStream.range(0, sortedUsers.size())
                .filter(i -> sortedUsers.get(i).getUserID().equals(userId.getUserID()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
    }

    // 중복되는 유저가 있으면 false
    public boolean checkUserExistence(String userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserID(userId));
        return user.isEmpty();
    }

    // 아이디 중복 확인 (추후 Service로 뺴기)
    public ResponseEntity<String> userIdCheck(User user) {
        if (!checkUserExistence(user.getUserID())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("아이디가 이미 사용 중입니다.");
        }
        return null;
    }

}