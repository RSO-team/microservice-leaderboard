package si.fri.rsoteam.services.beans;

import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rsoteam.entities.ScoreEntity;
import si.fri.rsoteam.entities.ScoreEntity;
import si.fri.rsoteam.lib.dtos.ScoreDto;
import si.fri.rsoteam.services.mappers.ScoreMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class ScoresBean {
    private Logger log = Logger.getLogger(ScoresBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed
    public ScoreDto getScore(Integer id){
        return ScoreMapper.entityToDto(em.find(ScoreEntity.class,id));
    }

    @Timed
    public List<ScoreDto> getAllScores(){
        return em.createNamedQuery("Score.getAll", ScoreEntity.class).getResultList().stream().map(ScoreMapper::entityToDto).collect(Collectors.toList());
    }

    public void deleteScore(Integer id) {
        ScoreEntity scoreEntity = em.find(ScoreEntity.class, id);
        if ( scoreEntity != null) {
            this.beginTx();
            em.remove(scoreEntity);
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
