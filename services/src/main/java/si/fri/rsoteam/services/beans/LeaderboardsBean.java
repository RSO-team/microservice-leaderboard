package si.fri.rsoteam.services.beans;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rsoteam.entities.LeaderboardEntity;
import si.fri.rsoteam.lib.dtos.LeaderboardDto;
import si.fri.rsoteam.services.mappers.ScoreMapper;
import si.fri.rsoteam.services.mappers.LeaderboardMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@RequestScoped
public class LeaderboardsBean {
    private Logger log = LogManager.getLogger(LeaderboardsBean.class.getName());

    @Inject
    private EntityManager em;
    @Inject
    private ScoresBean scoresBean;


    @Timed
    public LeaderboardDto getLeaderboard(Integer id){
        return LeaderboardMapper.entityToDto(em.find(LeaderboardEntity.class,id));
    }

    @Timed
    public List<LeaderboardDto> getAllLeaderboards(){
        return em.createNamedQuery("Leaderboard.getAll",LeaderboardEntity.class)
                .getResultList()
                .stream()
                .map(LeaderboardMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public LeaderboardDto createLeaderboard(LeaderboardDto leaderboardDto) {
        LeaderboardEntity leaderboardEntity = LeaderboardMapper.dtoToEntity(leaderboardDto);
        this.beginTx();
        em.persist(leaderboardEntity);
        this.commitTx();

        return LeaderboardMapper.entityToDto(leaderboardEntity);
    }

    public LeaderboardDto updateLeaderboard(LeaderboardDto leaderboardDto, Integer id) {
        this.beginTx();

        LeaderboardEntity leaderboardEntity = em.find(LeaderboardEntity.class, id);
        leaderboardEntity.setName(leaderboardDto.name);
        leaderboardEntity.setDescription(leaderboardDto.description);
        leaderboardEntity.setScores(leaderboardDto.scores.stream().map(ScoreMapper::dtoToEntity).collect(Collectors.toList()));
        leaderboardEntity.getScores().forEach(scoreEntity -> scoreEntity.setLeaderboard(leaderboardEntity));
        em.persist(leaderboardEntity);
        this.commitTx();

        return LeaderboardMapper.entityToDto(leaderboardEntity);
    }

    public void deleteLeaderboard(Integer id) {
        LeaderboardEntity leaderboardEntity = em.find(LeaderboardEntity.class, id);
        if (leaderboardEntity != null) {

            //  em.createQuery("DELETE FROM leaderboard_score WHERE leaderboard_score.id IN ( SELECT leaderboard_score.id from leaderboard_score where leaderboard_score.leaderboard_id = ?1)")
            //                  .setParameter(1,leaderboardEntity.getId()).getResultList();

            leaderboardEntity.getScores().forEach(scoreEntity -> scoresBean.deleteScore(scoreEntity.getId()));
            this.beginTx();
            em.remove(leaderboardEntity);
            this.commitTx();
        }
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
