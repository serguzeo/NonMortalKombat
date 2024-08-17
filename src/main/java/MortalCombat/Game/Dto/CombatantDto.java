package MortalCombat.Game.Dto;

import lombok.Data;

@Data
public class CombatantDto {
    private String name;
    private String iconPath;
    private int health;
    private int maxHealth;
    private int level;
    private int damage;
    private boolean isStunned;
    private int weakenFor;
}
