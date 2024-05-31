package com.example.school.rank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RankDTO {
    public RankDTO(String userID, Long rankPoint) {
        this.userID = userID;
        this.rankPoint = rankPoint;
    }

    private String userID;
    private Long rankPoint;
}