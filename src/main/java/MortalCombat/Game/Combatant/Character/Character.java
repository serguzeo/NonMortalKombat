package MortalCombat.Game.Combatant.Character;

import MortalCombat.Game.Combatant.Combatant;

import java.awt.*;

public interface Character {
    void doAction(Combatant combatant);
    Image getImage();
    String getName();
}
