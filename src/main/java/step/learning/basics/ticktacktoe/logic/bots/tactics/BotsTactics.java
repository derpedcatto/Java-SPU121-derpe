package step.learning.basics.ticktacktoe.logic.bots.tactics;

import step.learning.basics.ticktacktoe.model.GameSettings;

public interface BotsTactics {
    String BotInput(char[][] gameGrid, GameSettings gameSettings);
}
