package MortalCombat.Game.Combatant;

import lombok.Data;

@Data
public abstract class Combatant {
    private String name;
    private String iconPath;

    private int level = 1;
    private int HP;
    private int maxHP;

    private int damage = 16;

    private boolean isStunned;
    private int weakenedFor;

    public CombatantAction getAction() {
        return null;
    }
}
