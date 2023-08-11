package step.learning.basics.ticktacktoe;

import step.learning.basics.ticktacktoe.controller.MainMenuController;

public class TickTackToe {
    MainMenuController mainMenuController = new MainMenuController();
    public void init() {
        mainMenuController.start();
    }
}
