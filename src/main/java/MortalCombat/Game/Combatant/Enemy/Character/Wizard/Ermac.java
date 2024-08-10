package MortalCombat.Game.Combatant.Enemy.Character.Wizard;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Ermac implements Character {
    @Override
    public String getName() {
        return "Ермак";
    }

    @Override
    public String getIconPath() {
        return "img/characters/ermac.jpg";
    }
}
