package si.fri.rsoteam.services.mappers;

import si.fri.rsoteam.entities.ScoreEntity;
import si.fri.rsoteam.lib.dtos.ScoreDto;

public class ScoreMapper {
    public static ScoreDto entityToDto(ScoreEntity et) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.id = et.getId();
        scoreDto.userId = et.getUserId();
        scoreDto.score = et.getScore();

        return scoreDto;
    }

    public static ScoreEntity dtoToEntity(ScoreDto scoreDto){
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setUserId(scoreDto.userId);
        scoreEntity.setScore(scoreDto.score);

        return scoreEntity;
    }
}
