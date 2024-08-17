package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;


public class DefendAttackHandler extends AbstractCombatHandler {
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Первый игрок защищается
        int damage = 0;
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);

        String message = String.format("%s защищается!", firstCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
