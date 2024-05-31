package com.example.school.user;

import com.example.school.rank.RankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 회원가입
    public User save(User user){
        if (user.getRankPoint() == null) {
            user.setRankPoint(0L);
        }

        return userRepository.save(user);
    }

    // 모든 유저 반환
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // 유저 삭제
    public void delete(User user){
        userRepository.delete(user);
    }

    // 유저 로그인
    public boolean login(User user) {
        Optional<User> findUser = userRepository.findByUserIDAndPassword(user.getUserID(), user.getPassword());
        return findUser.isPresent();
    }

    public List<RankDTO> getTopFiveUserRanking() {
        return userRepository.findAll().stream()
                .map(user -> new RankDTO(user.getUserID(), user.getRankPoint()))
                .sorted(Comparator.comparingLong(RankDTO::getRankPoint).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

}