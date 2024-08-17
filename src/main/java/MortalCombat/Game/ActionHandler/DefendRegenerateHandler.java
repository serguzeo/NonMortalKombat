package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class DefendRegenerateHandler extends AbstractCombatHandler{
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Второй игрок восстанавливает здоровье
        int damage = 0;
        damage = handleWeakness(secondCombatant, firstCombatant, damage);
        firstCombatant.takeDamage(damage);
        int regeneratedXP = (secondCombatant.getMaxHP() - secondCombatant.getHP()) / 2;
        secondCombatant.setHP(secondCombatant.getHP() + regeneratedXP);

        String message = String.format("%s восстанавливает %dHP!", secondCombatant.getName(), regeneratedXP);
        return String.format(HANDLE_MESSAGE, message, damage, firstCombatant.getName());
    }
}
