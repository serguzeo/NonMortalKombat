package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.Character.Character;
import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;
import lombok.Setter;

public class Enemy extends Combatant {
    @Setter
    private EnemyType enemyType;
    private final Character character;

    public Enemy (int level, EnemyType enemyType, Character character) {
        setLevel(level);
        setEnemyType(enemyType);
        setHP(getMaxHP());
        setMaxHP(getMaxHP());
        this.character = character;
    }

    @Override
    public CombatantAction getAction() {
        return enemyType.getAction();
    }

    @Override
    public int getMaxHP() {
        return (int) (enemyType.getBaseHP() * (1 + 0.25 * (getLevel() - 1)));
    }

    @Override
    public int getDamage() {
        return (int) (enemyType.getBaseDamage() * (1 + 0.32 * (getLevel() - 1)));
    }

    @Override
    public String getName() {
        return character.getName() + " (" + enemyType.toString() + ")";
    }

    @Override
    public String getIconPath() {
        return character.getIconPath();
    }
}
