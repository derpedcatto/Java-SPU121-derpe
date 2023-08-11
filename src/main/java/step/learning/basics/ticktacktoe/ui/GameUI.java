package step.learning.basics.ticktacktoe.ui;

import step.learning.basics.ticktacktoe.utility.ConsoleColors;
import step.learning.basics.ticktacktoe.utility.GameChars;

public class GameUI {
    static public void DisplayGrid (char[][] grid, int width, int height) {
        char headerRowCharOne = 'A';
        char headerRowCharTwo = 'A';
        char headerRowMaxValue = '[';   // '[' comes after Z in ASCII table
        boolean headerRowTwoChars = false;

        char headerColCharOne = '1';
        char headerColCharTwo = '0';
        char headerColMaxValue = ':';
        boolean headerColTwoChars = false;

        // 0 is reserved for printing grid coordinates info around the field (A, B, ... | 1, 2, ...)
        // So when accessing grid array make sure to double-check array index
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                // Header Row
                if (i == 0) {
                    // Empty space between headers
                    if (j == 0) {
                        System.out.print("   ");
                        continue;
                    }

                    // "A  B  C..."
                    if (!headerRowTwoChars) {
                        System.out.print(headerRowCharOne + " ");
                        headerRowCharOne++;
                    }
                    // "AA AB AC..."
                    if (headerRowTwoChars) {
                        System.out.print(headerRowCharOne);
                        System.out.print(headerRowCharTwo);
                        headerRowCharTwo++;
                    }

                    // "... Z  AA AB AC..."
                    if (headerRowCharOne == headerRowMaxValue) {
                        headerRowTwoChars = true;
                        headerRowCharOne = 'A';
                    }
                    // "... AZ BA BB BC..."
                    if (headerRowCharTwo == headerRowMaxValue) {
                        headerRowCharOne++;
                        headerRowCharTwo = 'A';
                    }
                }

                // Header Column
                if (j == 0) {
                    // "1  2  3..."
                    if (!headerColTwoChars) {
                        System.out.print(headerColCharOne + " ");
                        headerColCharOne++;
                    }
                    // "11 12 13..."
                    if (headerColTwoChars) {
                        System.out.print(headerColCharOne);
                        System.out.print(headerColCharTwo);
                        headerColCharTwo++;
                    }

                    // "... 9  10 11 12..."
                    if (headerColCharOne == headerColMaxValue) {
                        headerColTwoChars = true;
                        headerColCharOne = '1';
                    }
                    // "... 19 20 21 22..."
                    if (headerColCharTwo == headerColMaxValue) {
                        headerColCharOne++;
                        headerColCharTwo = '0';
                    }
                }

                // Grid cells
                if (i != 0 && j != 0) {
                    String activeColor = "";
                    if (grid[i - 1][j - 1] != GameChars.EmptySpaceChar) {
                        activeColor = grid[i - 1][j - 1] == GameChars.Player1Char ? ConsoleColors.BLUE : ConsoleColors.RED;
                    }
                    System.out.print(activeColor + grid[i - 1][j - 1] + " " + ConsoleColors.RESET);
                }

                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

    static public void UserInputPrompt(int playerIndex) {
        System.out.print("Хід гравця " + playerIndex + " -> ");
    }

    static public void UserInputPrompt(int playerIndex, String step) { System.out.print("Хід гравця " + playerIndex + " -> " + step + "\n"); }
}
