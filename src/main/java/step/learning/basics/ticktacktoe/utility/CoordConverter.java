package step.learning.basics.ticktacktoe.utility;

import java.awt.Point;

public class CoordConverter {
    public static Point StringToPoint(String coordString) {
        int row = Integer.parseInt(coordString.replaceAll("[^0-9]", "")) - 1;
        int col = 0;
        for (int i = 0; i < coordString.length(); i++) {
            char c = coordString.charAt(i);
            if (Character.isLetter(c)) {
                col = col * 26 + (c - 'A' + 1);
            }
        }
        return new Point(col - 1, row);
    }

    public static String PointToString(Point coordPoint) {
        StringBuilder step = new StringBuilder();
        int x = coordPoint.x + 'A';
        int y = coordPoint.y + 1;

        // A - Z
        if (x <= 'Z') {
            step.append((char)x);
        }
        // AA - ZZ
        else {
            char c1 = 'A';
            char c2 = 'A';

            x = x - 'A' - 26;

            for (; x != 0; x--) {
                c2++;
                if (c2 > 'Z') {
                    c1++;
                    c2 = 'A';
                }
            }

            step.append(c1);
            step.append(c2);
        }

        step.append(y);
        return step.toString();
    }
}
