package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AADStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AAAAStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AWAWStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;

import java.util.Random;

public class Wizard implements EnemyType {
    private EnemyStrategy strategy;

    // Вероятности для стратегий
    private static final double AAD_STRATEGY_PROB = 0.33; // 33%
    private static final double AAAA_STRATEGY_PROB = 0.33; // 33%
    private static final double AWAW_STRATEGY_PROB = 0.34; // 34%

    private static final Random RANDOM = new Random();

    public Wizard() {
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

    private EnemyStrategy selectStrategy() {
        double chance = RANDOM.nextDouble();

        if (chance < AAD_STRATEGY_PROB) {
            return new AADStrategy();
        } else if (chance < AAD_STRATEGY_PROB + AAAA_STRATEGY_PROB) {
            return new AAAAStrategy();
        } else {
            return new AWAWStrategy();
        }
    }
}
