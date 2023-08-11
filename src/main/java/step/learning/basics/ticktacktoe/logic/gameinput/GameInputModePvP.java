package step.learning.basics.ticktacktoe.logic.gameinput;

import step.learning.basics.ticktacktoe.logic.gameinput.GameInputMode;
import step.learning.basics.ticktacktoe.controller.GameController;
import step.learning.basics.ticktacktoe.model.GameSettings;

public class GameInputModePvP extends GameInputMode {
    public String PlayerOneInput(char[][] gameGrid, GameSettings gameSettings) {
        return HumanInput(gameGrid, gameSettings, 1);
    }

    public String PlayerTwoInput(char[][] gameGrid, GameSettings gameSettings) {
        return HumanInput(gameGrid, gameSettings, 2);
    }
}