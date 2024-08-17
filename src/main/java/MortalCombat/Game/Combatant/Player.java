package MortalCombat.Game.Combatant;

import lombok.Getter;
import lombok.Setter;

public class Player extends Combatant {
    @Setter
    private CombatantAction action;

    @Override
    public CombatantAction getAction() {
        return action;
    }

    public Player(String playerName, String playerIconPath) {
        setHP(60);
        setMaxHP(60);
        setDamage(10);

        setName(playerName);
        setIconPath(playerIconPath);
    }
}
