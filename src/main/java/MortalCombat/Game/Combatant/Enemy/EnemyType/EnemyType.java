package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;

/**
 * Интерфейс, представляющий тип врага.
 * Каждый тип врага должен реализовать этот интерфейс, чтобы определить действия, здоровье и урон врага.
 */
public interface EnemyType {
    String toString();
    CombatantAction getAction();
    int getBaseHP();
    int getBaseDamage();
}
