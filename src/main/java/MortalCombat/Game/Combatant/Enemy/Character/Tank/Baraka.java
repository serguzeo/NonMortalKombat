package MortalCombat.Game.Combatant.Enemy.Character.Tank;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Baraka implements Character {
    @Override
    public String getName() {
        return "Барака";
    }

    @Override
    public String getIconPath() {
        return "img/characters/baraka.jpg";
    }
}
