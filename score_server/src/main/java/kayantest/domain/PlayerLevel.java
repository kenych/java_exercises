package kayantest.domain;

public class PlayerLevel {
    int playerId;
    int level;

    public PlayerLevel(int playerId, int level) {
        this.playerId = playerId;
        this.level = level;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerLevel that = (PlayerLevel) o;

        if (playerId != that.playerId) return false;
        if (level != that.level) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerId;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "PlayerLevel{" +
                "playerId=" + playerId +
                ", level=" + level +
                '}';
    }
}
