package MortalCombat.Game.Combatant.Enemy.Character.Fighter;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class GreatKungLao implements Character {
    @Override
    public String getName() {
        return "Великий Кунг Лао";
    }

    @Override
    public String getIconPath() {
        return "img/characters/greatkunglao.jpg";
    }
}
