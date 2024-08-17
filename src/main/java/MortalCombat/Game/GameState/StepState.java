package MortalCombat.Game.GameState;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;


public class StepState {
    private final LinkedList<Combatant> combatants = new LinkedList<>();
    private final LinkedList<CombatantAction> combatantActions = new LinkedList<>();

    public void addStep(Combatant combatant, CombatantAction combatantAction) {
        combatants.addLast(combatant);
        combatantActions.addLast(combatantAction);
    }

    public Pair<Combatant, CombatantAction> getNextStep() {
        return Pair.of(combatants.poll(), combatantActions.poll());
    }

    public int getSize() {
        return combatants.size();
    }

    public String getCombination() {
        return combatantActions.get(0) + "-" + combatantActions.get(1);
    }

    public void clear() {
        combatants.clear();
        combatantActions.clear();
    }
}
