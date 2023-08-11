package step.learning.basics.ticktacktoe.utility;

/**
 * Dynamic REGEX pattern generation for specific use cases.
 */
public class RegexGen {
    public static String StringCoordREGEX;

    /**
     * Generates an REGEX pattern based on game grid width and height. Must be generated once in controller constructor
     * (or multiple times if that is needed).
     * @param gridWidth
     * @param gridHeight
     * @return
     */
    public static void SetValidREGEX(int gridWidth, int gridHeight) {
        String columnRange = getColumnRange(gridWidth);
        String rowRange = getRowRange(gridHeight);

        String regex = "^([A-Z]{1,2})([1-9][0-9]{0,2})$";
        regex = regex.replace("CC", columnRange);
        regex = regex.replace("RR", rowRange);

        StringCoordREGEX = regex;
    }

    private static String getColumnRange(int gridWidth) {
        char start = 'A';
        char end = (char) ('A' + (gridWidth - 1));
        return "[" + start + "-" + end + "]";
    }

    private static String getRowRange(int gridHeight) {
        int start = 1;
        int end = Math.min(gridHeight, 99);
        return "[" + start + "-" + end + "]";
    }
}
