package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class RegenerateWeakenHandler extends AbstractCombatHandler{
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Второй игрок ослабляет первого на в полтора раза больше
        int damage = 0;
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);
        int weakenFor = (int) Math.round(secondCombatant.getLevel() * 1.5);
        firstCombatant.setWeakenFor(weakenFor);

        String message = String.format(
                "%s прерывает регенерацию врага<br>И ослабляет его на %d хода!",
                secondCombatant.getName(), weakenFor
        );
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
