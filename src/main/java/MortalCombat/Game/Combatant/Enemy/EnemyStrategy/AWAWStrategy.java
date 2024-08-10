package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

import java.util.ArrayDeque;
import java.util.Deque;

import static MortalCombat.Game.Combatant.CombatantAction.*;

public class AWAWStrategy implements EnemyStrategy {
    private final Deque<CombatantAction> strategy;

    public AWAWStrategy() {
        strategy = new ArrayDeque<>();

        strategy.add(ATTACK);
        strategy.add(WEAKEN);
        strategy.add(ATTACK);
        strategy.add(WEAKEN);
    }

    public CombatantAction getNextAction() {
        return strategy.poll();
    }
}
