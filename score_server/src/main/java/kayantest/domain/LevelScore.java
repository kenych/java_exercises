package kayantest.domain;

public class LevelScore {
    int level;
    int score;

    public LevelScore(int level, int score) {
        this.level = level;
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "LevelScore{" +
                "level=" + level +
                ", score=" + score +
                '}';
    }
}
