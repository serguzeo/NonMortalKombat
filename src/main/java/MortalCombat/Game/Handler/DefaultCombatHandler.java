package MortalCombat.Game.Handler;

import MortalCombat.Game.StepState;

public class DefaultCombatHandler implements CombatHandler {

    @Override
    public void handle(StepState state) {
        System.out.println("Подходящий обработчик не был найден");
    }
}
