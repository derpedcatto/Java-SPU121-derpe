package step.learning.basics.ticktacktoe.model;

public class GameSettings {
    public boolean opponentIsPlayer;
    public GameDifficulty difficulty;
    public int gridWidth;
    public int gridHeight;

    public GameSettings() {
        opponentIsPlayer = false;
        difficulty = GameDifficulty.NORMAL;
        gridWidth = 3;
        gridHeight = 3;
    }
}
