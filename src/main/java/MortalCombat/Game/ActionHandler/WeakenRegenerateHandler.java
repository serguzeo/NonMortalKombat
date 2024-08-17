package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class WeakenRegenerateHandler extends AbstractCombatHandler {
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Первый игрок ослабляет второго на в полтора раза больше
        int damage = 0;
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);
        int weakenFor = (int) Math.round(firstCombatant.getLevel() * 1.5);
        secondCombatant.setWeakenFor(weakenFor);

        String message = String.format(
                "%s прерывает регенерацию врага<br>И ослабляет его на %d хода!",
                firstCombatant.getName(), weakenFor
        );
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
