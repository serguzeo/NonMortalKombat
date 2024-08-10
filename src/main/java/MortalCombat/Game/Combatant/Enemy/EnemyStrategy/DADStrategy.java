package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

import java.util.ArrayDeque;
import java.util.Deque;

import static MortalCombat.Game.Combatant.CombatantAction.ATTACK;
import static MortalCombat.Game.Combatant.CombatantAction.DEFEND;

public class DADStrategy implements EnemyStrategy {
    private final Deque<CombatantAction> strategy;

    public DADStrategy() {
        strategy = new ArrayDeque<>();

        strategy.add(DEFEND);
        strategy.add(ATTACK);
        strategy.add(DEFEND);
    }

    public CombatantAction getNextAction() {
        return strategy.poll();
    }
}
