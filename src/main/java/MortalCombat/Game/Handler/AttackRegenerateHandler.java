package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class AttackRegenerateHandler extends AbstractCombatHandler{
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Первый игрок наносит двойной урон второму
        int damage = firstCombatant.getDamage() * 2;
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);

        String message = String.format("%s прерывает регенерацию врага!", firstCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
