package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class RegenerateAttackHandler extends AbstractCombatHandler{
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Второй игрок наносит двойной урон первому
        int damage = secondCombatant.getDamage() * 2;
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);

        String message = String.format("%s прерывает регенерацию врага!", secondCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
