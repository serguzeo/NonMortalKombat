package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.StepState;
import org.apache.commons.lang3.tuple.Pair;

public class DefendDefendHandler implements CombatHandler {
    @Override
    public void handle(StepState state) {
        Pair<Combatant, CombatantAction> first = state.getNextStep();
        Pair<Combatant, CombatantAction> second = state.getNextStep();

        System.out.println(first + " " + second);

    }
}
