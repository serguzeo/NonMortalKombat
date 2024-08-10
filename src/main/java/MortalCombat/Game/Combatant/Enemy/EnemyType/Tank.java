package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AAAAStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AADStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.DADStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;

import java.util.Random;

public class Tank implements EnemyType {
    private EnemyStrategy strategy;

    // Вероятности для стратегий
    private static final double AAD_STRATEGY_PROB = 0.30; // 30%
    private static final double DAD_STRATEGY_PROB = 0.60; // 60%
    private static final double AAAA_STRATEGY_PROB = 0.10; // 10%

    private static final Random RANDOM = new Random();

    public Tank() {
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
        return "Танк";
    }

    @Override
    public int getBaseHP() {
        return 100;
    }

    @Override
    public int getBaseDamage() {
        return 12;
    }

    private EnemyStrategy selectStrategy() {
        double chance = RANDOM.nextDouble();

        if (chance < AAD_STRATEGY_PROB) {
            return new AADStrategy();
        } else if (chance < AAD_STRATEGY_PROB + DAD_STRATEGY_PROB) {
            return new DADStrategy();
        } else {
            return new AAAAStrategy();
        }
    }
}
