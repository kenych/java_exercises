package kayantest.domain;

public class PlayerScore implements Comparable<PlayerScore> {
    int playerId;
    Integer score;

    public PlayerScore(int playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                "playerId=" + playerId +
                ", score=" + score +
                '}';
    }

    @Override
    public int compareTo(PlayerScore otherPlayerScore) {
        return score.compareTo(otherPlayerScore.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerScore that = (PlayerScore) o;

        if (playerId != that.playerId) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
