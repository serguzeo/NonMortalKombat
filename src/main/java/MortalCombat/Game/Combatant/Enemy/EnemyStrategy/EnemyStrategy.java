package MortalCombat.Game.Combatant.Enemy.EnemyStrategy;

import MortalCombat.Game.Combatant.CombatantAction;

/**
 * Интерфейс, представляющий стратегию поведения врага.
 * Классы, реализующие этот интерфейс, определяют, какое действие будет выполнено врагом в следующем шаге боя.
 */
public interface EnemyStrategy {
    CombatantAction getNextAction();
}
