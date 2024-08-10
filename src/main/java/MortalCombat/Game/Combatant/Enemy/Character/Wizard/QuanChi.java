package MortalCombat.Game.Combatant.Enemy.Character.Wizard;

import MortalCombat.Game.Combatant.Enemy.Character.Character;

public class QuanChi implements Character {
    @Override
    public String getName() {
        return "Куан Чи";
    }

    @Override
    public String getIconPath() {
        return "img/characters/quanchi.jpg";
    }
}
