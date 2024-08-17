package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class AttackDefendHandler extends AbstractCombatHandler {

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Второй игрок контратакует первого
        int damage = secondCombatant.getDamage() / 2;
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);

        String message = String.format("%s контратакует!", secondCombatant.getName());
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
