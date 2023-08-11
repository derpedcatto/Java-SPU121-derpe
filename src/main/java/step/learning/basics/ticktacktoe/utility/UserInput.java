package step.learning.basics.ticktacktoe.utility;
import java.util.Scanner;

/**
 * Collects human user input.
 */
public class UserInput {
    static private final Scanner scan = new Scanner(System.in);

    static public int getInt() {
        while (true) {
            try {
                return scan.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                scan.nextLine();
            }
        }
    }

    static public String getString() {
        return scan.nextLine();
    }
}