package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;

public interface EnemyType {
    String toString();
    CombatantAction getAction();
    int getBaseHP();
    int getBaseDamage();
}
