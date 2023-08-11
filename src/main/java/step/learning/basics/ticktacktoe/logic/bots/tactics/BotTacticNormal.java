package step.learning.basics.ticktacktoe.logic.bots.tactics;

import step.learning.basics.ticktacktoe.model.GameSettings;
import step.learning.basics.ticktacktoe.utility.CoordConverter;
import step.learning.basics.ticktacktoe.utility.GameChars;

import java.awt.Point;
import java.util.*;

public class BotTacticNormal implements BotsTactics {
    char[][] gameGrid;
    GameSettings gameSettings;

    @Override
    public String BotInput(char[][] gameGrid, GameSettings gameSettings) {
        this.gameGrid = gameGrid;
        this.gameSettings = gameSettings;

        return CoordConverter.PointToString(getBestMove());
    }

    private Point getBestMove() {
        Random rand = new Random();
        GridCell bestCell;
        List<GridCell> bestCellList = new ArrayList<>();
        List<GridCell> finalCellList = new ArrayList<>();
        int maxScore = 0;

        for (int row = 0; row < gameSettings.gridHeight - 2; row++) {
            for (int col = 0; col < gameSettings.gridWidth - 2; col++) {
                bestCell = evaluateGrid(new Point(col, row));
                if (bestCell != null) {
                    bestCellList.add(bestCell);
                }
            }
        }

        // Finding max score from list
        for (GridCell cell:
             bestCellList) {
            if (cell.score > maxScore) {
                maxScore = cell.score;
            }
        }

        // Excluding all worst options from list
        for (GridCell cell:
                bestCellList) {
            if (cell.score == maxScore) {
                finalCellList.add(cell);
            }
        }

        return finalCellList.get(rand.nextInt(finalCellList.size())).cell;
    }

    private GridCell evaluateGrid(Point topLeftCell) {
        Random rand = new Random();
        GridCell tempCell;
        List<GridCell> cellList = new ArrayList<>();
        List<GridCell> finalCellList = new ArrayList<>();
        Point p1;
        Point p2;
        Point p3;
        int maxScore = 0;

        // Straight Horizontal
        for (int row = 0; row <= 2; row++) {
            p1 = new Point(topLeftCell.x, topLeftCell.y + row);
            p2 = new Point(topLeftCell.x + 1, topLeftCell.y + row);
            p3 = new Point(topLeftCell.x + 2, topLeftCell.y + row);

            tempCell = evaluateLine(p1, p2, p3);
            if (tempCell != null) {
                cellList.add(tempCell);
            }
        }

        // Straight Vertical
        for (int col = 0; col <= 2; col++) {
            p1 = new Point(topLeftCell.x + col, topLeftCell.y);
            p2 = new Point(topLeftCell.x + col, topLeftCell.y + 1);
            p3 = new Point(topLeftCell.x + col, topLeftCell.y + 2);

            tempCell = evaluateLine(p1, p2, p3);
            if (tempCell != null) {
                cellList.add(tempCell);
            }
        }

        // Diagonal Right
        p1 = new Point(topLeftCell.x, topLeftCell.y);
        p2 = new Point(topLeftCell.x + 1, topLeftCell.y + 1);
        p3 = new Point(topLeftCell.x + 1, topLeftCell.y + 1);

        tempCell = evaluateLine(p1, p2, p3);
        if (tempCell != null) {
            cellList.add(tempCell);
        }

        // Diagonal Left
        p1 = new Point(topLeftCell.x + 2, topLeftCell.y);
        p2 = new Point(topLeftCell.x + 1, topLeftCell.y + 1);
        p3 = new Point(topLeftCell.x, topLeftCell.y + 2);

        tempCell = evaluateLine(p1, p2, p3);
        if (tempCell != null) {
            cellList.add(tempCell);
        }

        if (cellList.isEmpty()) {
            return null;
        }

        // Finding max score from list
        for (GridCell cell:
                cellList) {
            if (cell.score > maxScore) {
                maxScore = cell.score;
            }
        }

        // Excluding all worst options from list
        for (GridCell cell:
                cellList) {
            if (cell.score == maxScore) {
                finalCellList.add(cell);
            }
        }

        return finalCellList.get(rand.nextInt(finalCellList.size()));
    }

    /**
     * Goes through all possible combinations of grid line and returns the value of best possible potential move.
     */
    private GridCell evaluateLine(Point point1, Point point2, Point point3) {
        GridCell bestCell = new GridCell();
        char[] line = { gameGrid[point1.y][point1.x], gameGrid[point2.y][point2.x], gameGrid[point3.y][point3.x] };
        char e = GameChars.EmptySpaceChar;
        char p1 = GameChars.Player1Char;   // Human - X
        char p2 = GameChars.Player2Char;   // Bot - O

        // No empty space in line
        if (!(line[0] == e || line[1] == e || line[2] == e)) {
            return null;
        }

        // No characters
        if (Arrays.equals(line, new char[]{ e, e, e })) {
            bestCell.cell = lineRandomEmptyCell(point1, point2, point3);
            bestCell.score = 1;
        }


        // 1 Character


        // □ □ X | □ □ O
        else if (Arrays.equals(line, new char[]{ e, e, p1 })
              || Arrays.equals(line, new char[]{ e, e, p2 })) {
            bestCell.cell = point2;
            bestCell.score = 10;
        }
        // □ X □ | □ O □
        else if (Arrays.equals(line, new char[]{ e, p1, e })
              || Arrays.equals(line, new char[]{ e, p2, e })) {
            bestCell.cell = lineRandomEmptyCell(point1, null, point2);
            bestCell.score = 10;
        }
        // X □ □ | O □ □
        else if (Arrays.equals(line, new char[]{ p1, e, e })
              || Arrays.equals(line, new char[]{ p2, e, e })) {
            bestCell.cell = point2;
            bestCell.score = 10;
        }


        // 2 Characters


        // □ X X
        else if (Arrays.equals(line, new char[]{ e, p1, p1 })) {
            bestCell.cell = point1;
            bestCell.score = 100;
        }
        // □ O O
        else if (Arrays.equals(line, new char[]{ e, p2, p2 })) {
            bestCell.cell = point1;
            bestCell.score = 1000;
        }
        // □ X O | □ O X
        else if (Arrays.equals(line, new char[]{ e, p1, p2 })
              || Arrays.equals(line, new char[]{ e, p2, p1 })) {
            bestCell.cell = point1;
            bestCell.score = 10;
        }

        // X X □
        else if (Arrays.equals(line, new char[]{ p1, p1, e })) {
            bestCell.cell = point3;
            bestCell.score = 100;
        }
        // O O □
        else if (Arrays.equals(line, new char[]{ p2, p2, e })) {
            bestCell.cell = point3;
            bestCell.score = 1000;
        }
        // X O □ | O X □
        else if (Arrays.equals(line, new char[]{ p1, p2, e })
              || Arrays.equals(line, new char[]{ p2, p1, e })) {
            bestCell.cell = point3;
            bestCell.score = 10;
        }

        // X □ X
        else if (Arrays.equals(line, new char[]{ p1, e, p1 })) {
            bestCell.cell = point2;
            bestCell.score = 100;
        }
        // O □ O
        else if (Arrays.equals(line, new char[]{ p2, e, p2 })) {
            bestCell.cell = point2;
            bestCell.score = 1000;
        }
        // X □ O | O □ X
        else if (Arrays.equals(line, new char[]{ p1, e, p2 })
              || Arrays.equals(line, new char[]{ p2, e, p1 })) {
            bestCell.cell = point2;
            bestCell.score = 10;
        }

        return bestCell;
    }

    private Point lineRandomEmptyCell(Point p1, Point p2, Point p3) {
        Random rand = new Random();
        int choice = rand.nextInt(2);
        if (p1 == null) {
            return choice == 1 ? p2 : p3;
        }
        else if (p2 == null) {
            return choice == 1 ? p1 : p3;
        }
        else if (p3 == null) {
            return choice == 1 ? p1 : p2;
        }
        else {  // No Nulls
            choice = rand.nextInt(3);
            if (choice == 0) return p1;
            else if (choice == 1) return p2;
            else if (choice == 2) return p3;
        }
        return null;
    }
}

class GridCell {
    public Point cell;
    public int score;

    public GridCell() {
        cell = new Point(-1, -1);
        score = 0;
    }
}