package si.fri.rsoteam.lib.dtos;

import java.util.List;

public class LeaderboardDto {
    public Integer id;
    public String name;
    public String description;
    public List<ScoreDto> scores;

    public LeaderboardDto() {
    }
}
