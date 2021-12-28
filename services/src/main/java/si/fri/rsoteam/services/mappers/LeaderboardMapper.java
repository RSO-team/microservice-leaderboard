package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.entities.LeaderboardEntity;
import si.fri.rsoteam.lib.dtos.LeaderboardDto;

import java.util.stream.Collectors;

public class LeaderboardMapper {
    public static LeaderboardDto entityToDto(LeaderboardEntity et) {
        LeaderboardDto leaderboardDto = new LeaderboardDto();
        leaderboardDto.id = et.getId();
        leaderboardDto.name = et.getName();
        leaderboardDto.description = et.getDescription();
        leaderboardDto.scores = et.getScores().stream().map(ScoreMapper::entityToDto).collect(Collectors.toList());

        return leaderboardDto;
    }

    public static LeaderboardEntity dtoToEntity(LeaderboardDto leaderboardDto) {
        LeaderboardEntity leaderboardEntity = new LeaderboardEntity();
        leaderboardEntity.setName(leaderboardDto.name);
        leaderboardEntity.setDescription(leaderboardDto.description);
        leaderboardEntity.setScores(leaderboardDto.scores.stream().map(ScoreMapper::dtoToEntity).collect(Collectors.toList()));
        leaderboardEntity.getScores().forEach(scoreEntity -> scoreEntity.setLeaderboard(leaderboardEntity));
        return  leaderboardEntity;
    }
}
