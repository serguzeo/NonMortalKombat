package MortalCombat.Game.Combatant.Enemy.Character.Boss;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Goro implements Character {
    @Override
    public String getName() {
        return "Горо";
    }

    @Override
    public String getIconPath() {
        return "img/characters/goro.jpg";
    }
}
