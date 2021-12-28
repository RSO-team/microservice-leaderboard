package si.fri.rsoteam.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leaderboard")
@NamedQuery(name = "Leaderboard.getAll", query = "SELECT e from LeaderboardEntity e")
public class LeaderboardEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer id;


    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 100)
    private String description;

    @OneToMany(mappedBy = "leaderboard",cascade={CascadeType.PERSIST,CascadeType.REMOVE}, orphanRemoval=true)
    private List<ScoreEntity> scores = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ScoreEntity> getScores() {
        return scores;
    }

    public void setScores(List<ScoreEntity> scores) {
        this.scores = scores;
    }
}
