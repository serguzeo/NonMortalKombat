package MortalCombat.Game.Combatant.Enemy.EnemyType;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyStrategy.EnemyStrategy;

public interface EnemyType {
    CombatantAction getAction();
}
