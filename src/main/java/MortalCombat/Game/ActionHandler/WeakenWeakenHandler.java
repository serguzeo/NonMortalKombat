package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class WeakenWeakenHandler extends AbstractCombatHandler {

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Ослабляет первый
        int damage = (int) ((float) firstCombatant.getDamage() * 1.15);
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);
        secondCombatant.setWeakenFor(firstCombatant.getLevel());

        String message = String.format("%s ослабляет первым!", firstCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
