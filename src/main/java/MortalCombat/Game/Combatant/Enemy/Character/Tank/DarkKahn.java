package MortalCombat.Game.Combatant.Enemy.Character.Tank;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class DarkKahn implements Character {
    @Override
    public String getName() {
        return "Дарк Кан";
    }

    @Override
    public String getIconPath() {
        return "img/characters/darkkahn.jpg";
    }
}
