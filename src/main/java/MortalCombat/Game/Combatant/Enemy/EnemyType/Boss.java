package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.AARStrategy;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;


public class Boss implements EnemyType {
    private EnemyStrategy strategy;
    private final int playerHP;

    // Вероятности для стратегий
    private static final double AAR_STRATEGY_PROB = 1.00; // 100%

    public Boss(int playerHp) {
        this.strategy = selectStrategy();
        playerHP = playerHp * 2;
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
        return "Босс";
    }

    @Override
    public int getBaseHP() {
        return playerHP;
    }

    @Override
    public int getBaseDamage() {
        return 26;
    }

    private EnemyStrategy selectStrategy() {
        return new AARStrategy();
    }

}
