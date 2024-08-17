package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class AttackAttackHandler extends AbstractCombatHandler {

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Урон наносит первый игрок
        int damage = firstCombatant.getDamage();
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);

        String message = String.format("%s атакует первее!", firstCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
