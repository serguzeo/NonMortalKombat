package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class WeakenAttackHandler extends AbstractCombatHandler {

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Второй игрок срывает попытку ослабить
        int damage = (int) ((float) secondCombatant.getDamage() * 1.15);
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);

        String message = String.format("%s срывает попытку ослабить!", secondCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
