package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.GameState.StepState;

public class DefaultCombatHandler implements CombatHandler {

    @Override
    public String handle(StepState state) {
        System.out.println("Подходящий обработчик не был найден");
        return "error :(";
    }
}
