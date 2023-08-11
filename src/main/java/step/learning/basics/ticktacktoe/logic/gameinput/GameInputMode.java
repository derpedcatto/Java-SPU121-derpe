package step.learning.basics.ticktacktoe.logic.gameinput;

import step.learning.basics.ticktacktoe.logic.bots.tactics.BotTacticNormal;
import step.learning.basics.ticktacktoe.logic.bots.tactics.BotTacticRandom;
import step.learning.basics.ticktacktoe.logic.bots.tactics.BotsTactics;
import step.learning.basics.ticktacktoe.model.GameSettings;
import step.learning.basics.ticktacktoe.ui.GameUI;
import step.learning.basics.ticktacktoe.utility.CoordConverter;
import step.learning.basics.ticktacktoe.utility.RegexGen;
import step.learning.basics.ticktacktoe.utility.UserInput;

import java.awt.Point;
import java.util.regex.Pattern;

public abstract class GameInputMode {
    public String PlayerOneInput(char[][] gameGrid, GameSettings gameSettings) {
        return "'PlayerOneInput' not implemented!";
    }
    public String PlayerTwoInput(char[][] gameGrid, GameSettings gameSettings) {
        return "'PlayerTwoInput' not implemented!";
    }


    protected final boolean StepIsValid(String userStep, char[][] gameGrid, GameSettings gameSettings) {
        // String coord is valid
        if (Pattern.matches(RegexGen.StringCoordREGEX, userStep)) {
            Point userCoord = CoordConverter.StringToPoint(userStep);

            // Point is not out of bounds
            if ((userCoord.y >= 0 && userCoord.y < gameSettings.gridHeight) && (userCoord.x >= 0 && userCoord.x < gameSettings.gridWidth)) {

                // Game grid point is empty
                if (gameGrid[userCoord.y][userCoord.x] != 'X' && gameGrid[userCoord.y][userCoord.x] != 'O') {
                    return true;
                }
            }
        }
        return false;
    }


    protected final String HumanInput(char[][] gameGrid, GameSettings gameSettings, int playerIndex) {
        boolean isValid = false;
        String step;

        do {
            GameUI.UserInputPrompt(playerIndex);
            step = UserInput.getString().toUpperCase();
            isValid = StepIsValid(step, gameGrid, gameSettings);
        } while (!isValid);

        return step;
    }

    protected final String BotInput(char[][] gameGrid, GameSettings gameSettings, int playerIndex) {
        BotsTactics tactic;
        switch (gameSettings.difficulty) {
            case RANDOM:
                tactic = new BotTacticRandom();
                break;
            case NORMAL:
                tactic = new BotTacticNormal();
                break;
            default:
                tactic = new BotTacticRandom();
                break;
        }

        String result = tactic.BotInput(gameGrid, gameSettings);
        GameUI.UserInputPrompt(playerIndex, result);
        return result;
    }
}