package MortalCombat.Game.Combatant;

import MortalCombat.Game.Combatant.Character.Character;

import java.util.List;

public interface Combatant {

    void doActionWith(CombatantAction combatantAction, Combatant opponent);

    void takeDamage(int damage);

    boolean isAlive();

    String getName();
}
