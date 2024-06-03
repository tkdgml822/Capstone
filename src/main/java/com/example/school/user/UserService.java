package com.example.school.user;

import com.example.school.rank.RankDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public int getUserRank(String username) {
        log.info("userid={}", username);
        User userId = userRepository.findByUserID(username);

        List<User> users = userRepository.findAll();
        // 여기서 정렬 순서를 반대로 합니다.
        List<User> sortedUsers = users.stream()
                .sorted(Comparator.comparingLong(User::getRankPoint).reversed())
                .toList();

        int resultRank = getResultRank(sortedUsers, userId);

        return resultRank + 1;
    }

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
}