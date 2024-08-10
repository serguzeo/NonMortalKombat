package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;

public class Enemy extends Combatant {
    private EnemyType enemyType;
    private int level = 2;

    @Override
    public CombatantAction getAction() {
        return enemyType.getAction();
    }
}
