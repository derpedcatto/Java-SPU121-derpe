package step.learning.basics.ticktacktoe.logic.gameinput;

import step.learning.basics.ticktacktoe.model.GameSettings;

public class GameInputModePvE extends GameInputMode {
    @Override
    public String PlayerOneInput(char[][] gameGrid, GameSettings gameSettings) {
        return HumanInput(gameGrid, gameSettings, 1);
    }

    @Override
    public String PlayerTwoInput(char[][] gameGrid, GameSettings gameSettings) {
        return BotInput(gameGrid, gameSettings, 2);
    }
}