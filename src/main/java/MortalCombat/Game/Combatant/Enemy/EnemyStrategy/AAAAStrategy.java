package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static MortalCombat.Game.Combatant.CombatantAction.ATTACK;

public class AAAAStrategy implements EnemyStrategy {
    private final Deque<CombatantAction> strategy;

    public AAAAStrategy() {
        strategy = new ArrayDeque<>();

        strategy.add(ATTACK);
        strategy.add(ATTACK);
        strategy.add(ATTACK);
        strategy.add(ATTACK);
    }

    public CombatantAction getNextAction() {
        return strategy.poll();
    }
}
