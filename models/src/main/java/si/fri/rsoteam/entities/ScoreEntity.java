package si.fri.rsoteam.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "score")
@NamedQuery(name = "Score.getAll", query = "SELECT e from ScoreEntity e")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;

    private Integer userId;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    private LeaderboardEntity leaderboard;

    public LeaderboardEntity getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(LeaderboardEntity leaderboard) {
        this.leaderboard = leaderboard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}
