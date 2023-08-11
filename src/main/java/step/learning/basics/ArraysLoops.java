package step.learning.basics;

import java.util.Random;
import java.util.Scanner;

/**
 * Робота з масивами даниї. Ітерування.
 */
public class ArraysLoops {
    private final Random random = new Random(); // final = const/readonly/sealed

    /**
     * Запуск демонстрації
     */
    public void demo() {
        multiArrayDemo();
    }

    private void singleArrayDemo() {
        int[] arr1 = { 1, 2, 3, 4, 5 };
        int[] arr2 = new int[] { 1, 2, 3, 4, 5 };
        Scanner kbScanner = new Scanner(System.in);

        System.out.print("Введіть розмір масиву len: ");
        int len;
        do {
            String str = kbScanner.next();
            try {
                len = Integer.parseInt(str);
                if (len <= 0) {
                    System.err.print("Введіть позитивне значення len: ");
                }
            }
            catch(NumberFormatException ignored) {
                System.err.print("Введіть числове значення  len: ");
                len = 0;
            }
        } while(len <= 0);

        int[] arr3 = new int[len];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = random.nextInt(21 - 10);
        }

        System.out.println("arr1: ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();

        for (int x : arr3) {
            System.out.print(x + " ");
        }
        System.out.println();

        kbScanner.close();
    }

    private void multiArrayDemo() {
        int[][] arr = {
                { 1, 2, 3, 4, 5 },
                { 6, 7, 8, 9 }
        };
        for (int[] line : arr) {
            for (int x : line) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        System.out.println("-----------------");

        int size = 5;
        int[][] triangle = new int[5][];
        for (int i = 0; i < size; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                triangle[i][j] = random.nextInt(10);
            }
        }
        for (int[] line : triangle) {
            for (int x : line) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
