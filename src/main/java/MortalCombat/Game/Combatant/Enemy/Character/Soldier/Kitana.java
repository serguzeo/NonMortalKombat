package MortalCombat.Game.Combatant.Enemy.Character.Soldier;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Kitana implements Character {
    @Override
    public String getName() {
        return "Китана";
    }

    @Override
    public String getIconPath() {
        return "img/characters/kitana.jpg";
    }
}
