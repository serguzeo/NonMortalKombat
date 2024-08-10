package MortalCombat.Game.Combatant.Enemy.Character.Soldier;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class Kronika implements Character {
    @Override
    public String getName() {
        return "Кроника";
    }

    @Override
    public String getIconPath() {
        return "img/characters/kronica.jpg";
    }
}
