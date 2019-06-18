package rezaei.model;

public class Records {
    private Long playerId;
    private int score;
    private int rank;
    public Records(Long playerId, int score, int rank) {
        this.playerId = playerId;
        this.score = score;
        this.rank = rank;
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
}

