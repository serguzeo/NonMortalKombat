package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

import java.util.ArrayDeque;
import java.util.Deque;
import static MortalCombat.Game.Combatant.CombatantAction.ATTACK;
import static MortalCombat.Game.Combatant.CombatantAction.REGENERATE;

public class AARStrategy implements EnemyStrategy{
    private final Deque<CombatantAction> strategy;

    public AARStrategy() {
        strategy = new ArrayDeque<>();

        strategy.add(ATTACK);
        strategy.add(ATTACK);
        strategy.add(REGENERATE);
    }

    @Override
    public CombatantAction getNextAction() {
        return strategy.poll();
    }
}
