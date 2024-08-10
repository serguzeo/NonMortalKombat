package MortalCombat.Game.Combatant;

import lombok.Data;

@Data
public abstract class Combatant {
    private int HP;
    private int maxHP;

    private int damage;

    private boolean isStunned;
    private int weakenedFor;

    public CombatantAction getAction() {
        return null;
    }
}
