package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class RegenerateDefendHandler extends AbstractCombatHandler{
    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        // Первый игрок восстанавливает здоровье
        int damage = 0;
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);
        int regeneratedXP = (firstCombatant.getMaxHP() - firstCombatant.getHP()) / 2;
        firstCombatant.setHP(firstCombatant.getHP() + regeneratedXP);

        String message = String.format("%s восстанавливает %dHP!", firstCombatant.getName(), regeneratedXP);
        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
