package step.learning.basics.ticktacktoe.controller;

import step.learning.basics.ticktacktoe.model.GameDifficulty;
import step.learning.basics.ticktacktoe.model.GameSettings;
import step.learning.basics.ticktacktoe.model.GameStats;
import step.learning.basics.ticktacktoe.ui.MainMenu;
import step.learning.basics.ticktacktoe.utility.UserInput;

public class MainMenuController {
    private GameSettings gameSettings = new GameSettings();
    private GameStats gameStats = new GameStats();
    private GameController gameController;

    public void start() {
        while (true) {
            int menuResult;
            menuResult = MainMenuLogic();

            // Exit
            if (menuResult == 0) return;

            // Starting game
            else if (menuResult == 4) {
                int winnerIndex;
                gameController = new GameController(gameSettings, gameStats);
                winnerIndex = gameController.start();

                // After game end
                gameStats = gameController.stats;
                MainMenu.GameOverMenu(winnerIndex);
                MainMenu.StatsMenu(gameStats);
            }
        }
    }

    private int MainMenuLogic() {
        int chosenOption;
        MainMenu.TopLevelMenu();

        chosenOption = UserInput.getInt();
        switch(chosenOption) {
            // Exit
            case 0:
                return 0;
            // Choose opponent
            case 1:
                MainMenu.OpponentMenu(1);
                chosenOption = UserInput.getInt();
                if (chosenOption == 1) {
                    gameSettings.opponentIsPlayer = true;
                }
                else if (chosenOption == 2) {
                    gameSettings.opponentIsPlayer = false;
                    do {
                        MainMenu.OpponentMenu(2);
                        chosenOption = UserInput.getInt();
                    } while (chosenOption <= 0 || chosenOption > GameDifficulty.values().length);
                    gameSettings.difficulty = GameDifficulty.values()[chosenOption - 1];
                }
                return 1;
            // Grid settings
            case 2:
                do {
                    MainMenu.GridSettingsMenu(1);
                    gameSettings.gridWidth = UserInput.getInt();
                } while (gameSettings.gridWidth < 3 || gameSettings.gridWidth > 99);
                do {
                    MainMenu.GridSettingsMenu(2);
                    gameSettings.gridHeight = UserInput.getInt();
                } while (gameSettings.gridHeight < 3 || gameSettings.gridHeight > 99);

                return 2;
            // Stats
            case 3:
                MainMenu.StatsMenu(gameStats);
                return 3;
            // Start game
            case 4:
                return 4;
            default:

        }

        return -1;
    }
}
