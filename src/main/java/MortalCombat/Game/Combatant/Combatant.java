package MortalCombat.Game.Combatant;

import lombok.Data;

@Data
public abstract class Combatant {
    private String name = "Combatant";
    private String iconPath;

    private int level = 1;
    private int HP = 80;
    private int maxHP = 80;

    private int damage = 16;

    private boolean isStunned = false;
    private int weakenFor = 0;

    public abstract CombatantAction getAction();

    public void takeDamage(int damage) {
        setHP(getHP() - damage);
    }
}
