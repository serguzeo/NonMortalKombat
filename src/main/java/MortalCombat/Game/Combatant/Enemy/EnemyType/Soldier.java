package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AADStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.DADStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;

import java.util.Random;

public class Soldier implements EnemyType {
    private EnemyStrategy strategy;

    // Вероятности для стратегий
    private static final double AAD_STRATEGY_PROB = 0.50; // 50%
    private static final double DAD_STRATEGY_PROB = 0.50; // 50%

    private static final Random RANDOM = new Random();

    public Soldier() {
        this.strategy = selectStrategy();
    }

    @Override
    public CombatantAction getAction() {
        var next = strategy.getNextAction();

        if (next != null) {
            return next;
        } else {
            strategy = selectStrategy();
            return getAction();
        }
    }

    @Override
    public String toString() {
        return "Солдат";
    }

    @Override
    public int getBaseHP() {
        return 80;
    }

    @Override
    public int getBaseDamage() {
        return 16;
    }

    private EnemyStrategy selectStrategy() {
        double chance = RANDOM.nextDouble();

        if (chance < AAD_STRATEGY_PROB) {
            return new AADStrategy();
        } else {
            return new DADStrategy();
        }
    }
}
