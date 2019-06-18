package rezaei.model;

import java.util.List;

public class Score {
    private long  playerId;
    private  int score;
    private  int rank;
    List records;

    public Score(long playerId, int score, int rank, List records) {
        this.playerId = playerId;
        this.score = score;
        this.rank = rank;
        this.records = records;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
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
