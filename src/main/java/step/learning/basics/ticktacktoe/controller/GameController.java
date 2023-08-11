package step.learning.basics.ticktacktoe.controller;

import step.learning.basics.ticktacktoe.model.GameSettings;
import step.learning.basics.ticktacktoe.model.GameStats;
import step.learning.basics.ticktacktoe.ui.GameUI;
import step.learning.basics.ticktacktoe.logic.gameinput.*;
import step.learning.basics.ticktacktoe.utility.CoordConverter;
import step.learning.basics.ticktacktoe.utility.GameChars;
import step.learning.basics.ticktacktoe.utility.RegexGen;

import java.awt.*;
import java.util.Arrays;

public class GameController {
    /**
     * Game settings.
     */
    public final GameSettings settings;

    /**
     * Interface for chosen game input mode (P1 vs P2 or P1 vs AI).
     */
    public final GameInputMode gameInputMode;

    /**
     * Player 1 global stats.
     */
    public final GameStats stats;

    /**
     * Main game grid.
     */
    public char[][] gameGrid;



    /**
     * Default constructor.
     * @param settings Game settings.
     * @param stats Player 1 global stats.
     */
    public GameController(GameSettings settings, GameStats stats) {
        this.settings = settings;
        this.stats = stats;
        gameInputMode = settings.opponentIsPlayer ? new GameInputModePvP() : new GameInputModePvE();

        gameGrid = new char[settings.gridHeight][settings.gridWidth];
        for (char[] row : gameGrid) {
            Arrays.fill(row, GameChars.EmptySpaceChar);
        }

        RegexGen.SetValidREGEX(settings.gridWidth, settings.gridHeight);
    }



    /**
     * Game initialization.
     */
    public int start() {
        Point coordPoint;
        String coordString;
        int winnerIndex = 0;

        GameUI.DisplayGrid(gameGrid, settings.gridWidth, settings.gridHeight);
        do {
            coordString = gameInputMode.PlayerOneInput(gameGrid, settings);
            coordPoint = CoordConverter.StringToPoint(coordString);
            gameGrid[coordPoint.y][coordPoint.x] = GameChars.Player1Char;
            GameUI.DisplayGrid(gameGrid, settings.gridWidth, settings.gridHeight);

            winnerIndex = WinCheck();
            if (winnerIndex != 0) break;

            coordString = gameInputMode.PlayerTwoInput(gameGrid, settings);
            coordPoint = CoordConverter.StringToPoint(coordString);
            gameGrid[coordPoint.y][coordPoint.x] = GameChars.Player2Char;
            GameUI.DisplayGrid(gameGrid, settings.gridWidth, settings.gridHeight);

            winnerIndex = WinCheck();
        } while (winnerIndex == 0);

        if (winnerIndex == 1) {
            stats.winsP1Count++;
        }
        else if (winnerIndex == -1) {
            // Draw
        }
        else {
            stats.winsP2Count++;
        }

        return winnerIndex;
    }

    private int WinCheck() {
        int winnerIndex = 0;

        // Checking every possible combination on grid
        for (int gridY = 1; gridY < settings.gridHeight - 1; gridY++) {
            for (int gridX = 1; gridX < settings.gridWidth - 1; gridX++) {
                // Checking 3x3 cubes for 3-match
                int matchCountP1 = 0;
                int matchCountP2 = 0;

                // 3 Horizontal Straight
                for (int rangeY = -1; rangeY <= 1; rangeY++) {
                    for (int rangeX = -1; rangeX <= 1; rangeX++) {
                        if (gameGrid[gridY + rangeY][gridX + rangeX] == GameChars.Player1Char) {
                            matchCountP1++;
                        }
                        if (gameGrid[gridY + rangeY][gridX + rangeX] == GameChars.Player2Char) {
                            matchCountP2++;
                        }
                    }
                    if (matchCountP1 == 3) {
                        winnerIndex = 1;
                        return winnerIndex;
                    }
                    else if (matchCountP2 == 3) {
                        winnerIndex = 2;
                        return winnerIndex;
                    }

                    matchCountP1 = 0;
                    matchCountP2 = 0;
                }

                // 3 Vertical Straight
                for (int rangeX = -1; rangeX <= 1; rangeX++) {
                    for (int rangeY = -1; rangeY <= 1; rangeY++) {
                        if (gameGrid[gridY + rangeY][gridX + rangeX] == GameChars.Player1Char) {
                            matchCountP1++;
                        }
                        if (gameGrid[gridY + rangeY][gridX + rangeX] == GameChars.Player2Char) {
                            matchCountP2++;
                        }
                    }
                    if (matchCountP1 == 3) {
                        winnerIndex = 1;
                        return winnerIndex;
                    }
                    else if (matchCountP2 == 3) {
                        winnerIndex = 2;
                        return winnerIndex;
                    }

                    matchCountP1 = 0;
                    matchCountP2 = 0;
                }

                // Diagonal Left -> Right
                for (int i = -1; i <= 1; i++) {
                    if (gameGrid[gridY + i][gridX + i] == GameChars.Player1Char) {
                        matchCountP1++;
                    }
                    if (gameGrid[gridY + i][gridX + i] == GameChars.Player2Char) {
                        matchCountP2++;
                    }
                }
                if (matchCountP1 == 3) {
                    winnerIndex = 1;
                    return winnerIndex;
                }
                else if (matchCountP2 == 3) {
                    winnerIndex = 2;
                    return winnerIndex;
                }
                matchCountP1 = 0;
                matchCountP2 = 0;

                // Diagonal Right -> Left
                for (int i = 1; i >= -1; i--) {
                    if (gameGrid[gridY - i][gridX + i] == GameChars.Player1Char) {
                        matchCountP1++;
                    }
                    if (gameGrid[gridY - i][gridX + i] == GameChars.Player2Char) {
                        matchCountP2++;
                    }
                }
                if (matchCountP1 == 3) {
                    winnerIndex = 1;
                    return winnerIndex;
                }
                else if (matchCountP2 == 3) {
                    winnerIndex = 2;
                    return winnerIndex;
                }
                matchCountP1 = 0;
                matchCountP2 = 0;
            }
        }

        // Checking for draw
        boolean isDraw = true;
        for (int i = 0; i < settings.gridHeight; i++) {
            for (int j = 0; j < settings.gridWidth; j++) {
                if (gameGrid[i][j] == GameChars.EmptySpaceChar) {
                    isDraw = false;
                }
            }
        }
        if (isDraw) winnerIndex = -1;

        return winnerIndex;
    }
}