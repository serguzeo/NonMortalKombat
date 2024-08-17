package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class AttackWeakenHandler extends AbstractCombatHandler {

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Первый игрок срывает попытку ослабить
        int damage = (int) ((float) firstCombatant.getDamage() * 1.15);
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);

        String message = String.format("%s срывает попытку ослабить!", firstCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
