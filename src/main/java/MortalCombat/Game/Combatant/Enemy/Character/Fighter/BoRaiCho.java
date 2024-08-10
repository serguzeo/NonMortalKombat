package MortalCombat.Game.Combatant.Enemy.Character.Fighter;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class BoRaiCho implements Character {
    @Override
    public String getName() {
        return "Бо’Рай Чо";
    }

    @Override
    public String getIconPath() {
        return "img/characters/boraicho.jpg";
    }
}
