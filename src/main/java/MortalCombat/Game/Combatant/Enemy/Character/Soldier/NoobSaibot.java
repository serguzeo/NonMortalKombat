package MortalCombat.Game.Combatant.Enemy.Character.Soldier;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class NoobSaibot implements Character {
    @Override
    public String getName() {
        return "Нуб Сайбот";
    }

    @Override
    public String getIconPath() {
        return "img/characters/noob.jpg";
    }
}
