package step.learning.basics.ticktacktoe.logic.bots.tactics;

import step.learning.basics.ticktacktoe.model.GameSettings;
import step.learning.basics.ticktacktoe.utility.CoordConverter;
import step.learning.basics.ticktacktoe.utility.GameChars;

import java.awt.Point;
import java.util.Random;

public class BotTacticRandom implements BotsTactics {
    @Override
    public String BotInput(char[][] gameGrid, GameSettings gameSettings) {
        Random random = new Random();
        Point coordPoint = new Point();

        do {
            coordPoint.x = random.nextInt(gameSettings.gridWidth);
            coordPoint.y = random.nextInt(gameSettings.gridHeight);
        } while (gameGrid[coordPoint.y][coordPoint.x] != GameChars.EmptySpaceChar);

        return CoordConverter.PointToString(coordPoint);
    }
}
