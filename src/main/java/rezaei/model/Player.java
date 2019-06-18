package rezaei.model;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
public class Player {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long playerId;

    @Min(value = 0, message = "invalid_score")
    @Max(value = 100, message = "invalid_score")
    private int score;
    private int rank;
    private long timestamp=System.currentTimeMillis();

    public Player() {
        super();
    }
    public Player(Long playerId, int score, int rank) {
        super();
        this.playerId = playerId;
        this.score = score;
        this.rank = rank;
    }
    public Player(Long playerId, int score, int rank, long timestamp) {
        super();
        this.playerId = playerId;
        this.score = score;
        this.rank = rank;
        this.timestamp = timestamp;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
