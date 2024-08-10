package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

import java.util.ArrayDeque;
import java.util.Deque;

import static MortalCombat.Game.Combatant.CombatantAction.ATTACK;
import static MortalCombat.Game.Combatant.CombatantAction.DEFEND;

public class AADStrategy implements EnemyStrategy {
    private final Deque<CombatantAction> strategy;

    public AADStrategy() {
        strategy = new ArrayDeque<>();

        strategy.add(ATTACK);
        strategy.add(ATTACK);
        strategy.add(DEFEND);
    }

    public CombatantAction getNextAction() {
        return strategy.poll();
    }
}
