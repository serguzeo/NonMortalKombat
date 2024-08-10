package MortalCombat.Game.Combatant.Enemy.Character.Wizard;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Blaze implements Character {
    @Override
    public String getName() {
        return "Блейз";
    }

    @Override
    public String getIconPath() {
        return "img/characters/blaze.jpg";
    }
}
