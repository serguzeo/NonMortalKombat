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
        setHP(80);
        setMaxHP(80);

        setName(playerName);
        setIconPath(playerIconPath);
    }
}
