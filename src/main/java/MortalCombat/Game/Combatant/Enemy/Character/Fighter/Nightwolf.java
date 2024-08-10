package MortalCombat.Game.Combatant.Enemy.Character.Fighter;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Nightwolf implements Character {
    @Override
    public String getName() {
        return "Ночной Волк";
    }

    @Override
    public String getIconPath() {
        return "img/characters/nightwolf.jpg";
    }
}
