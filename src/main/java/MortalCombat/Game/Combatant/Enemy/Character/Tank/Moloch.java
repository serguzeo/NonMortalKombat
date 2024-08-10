package MortalCombat.Game.Combatant.Enemy.Character.Tank;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Moloch implements Character {
    @Override
    public String getName() {
        return "Молох";
    }

    @Override
    public String getIconPath() {
        return "img/characters/moloch.jpg";
    }
}
