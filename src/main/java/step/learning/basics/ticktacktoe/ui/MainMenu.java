package step.learning.basics.ticktacktoe.ui;

import step.learning.basics.ticktacktoe.model.GameStats;
import step.learning.basics.ticktacktoe.utility.ConsoleColors;

public class MainMenu {
    static public void TopLevelMenu() {
        System.out.println("\n\nГоловне меню");
        System.out.println("0 - Вихід");
        System.out.println("1 - Вибір опонента / Складність гри");
        System.out.println("2 - Розміри сітки");
        System.out.println("3 - Статистика");
        System.out.println("4 - Розпочати гру");
        System.out.print("--> ");
    }

    static public void OpponentMenu(int index) {
        switch (index) {
            // Top menu
            case 1:
                System.out.println("\n1 - Опонент Людина");
                System.out.println("2 - Опонент ШІ");
                System.out.println("3 - Назад");
                System.out.print("--> ");
                break;
            // AI Difficulty
            case 2:
                System.out.println("\n1 - Рандомний ШІ");
                System.out.println("2 - Нормальний ШІ");
                System.out.print("--> ");
                break;
        }

    }

    static public void GridSettingsMenu(int index) {
        switch (index) {
            case 0:
                System.out.print("\nРозмір поля -> ");
                break;
            // Grid width - UNUSED
            case 1:
                System.out.print("\nШирина поля -> ");
                break;
            // Grid height - UNUSED
            case 2:
                System.out.print("Висота поля -> ");
                break;
        }
    }

    static public void StatsMenu(GameStats stats) {
        System.out.println(ConsoleColors.GREEN + "\nВиграшів Гравця 1: " + stats.winsP1Count + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "Виграшів Гравця 2: " + stats.winsP2Count + ConsoleColors.RESET);
        System.out.println();
    }

    static public void GameOverMenu(int playerIndex) {
        if (playerIndex == -1) {
            System.out.println(ConsoleColors.PURPLE + "\nНічия!\n" + ConsoleColors.RESET);
            return;
        }
        System.out.println(ConsoleColors.GREEN + "\nГравець " + playerIndex + " Виграв!\n" + ConsoleColors.RESET);
    }
}