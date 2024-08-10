package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;

public class Enemy extends Combatant {
    private EnemyType enemyType;


    @Override
    public CombatantAction getAction() {
        return enemyType.getAction();
    }
}
