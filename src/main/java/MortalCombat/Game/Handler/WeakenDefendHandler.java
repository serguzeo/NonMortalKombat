package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import java.util.Random;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class WeakenDefendHandler extends AbstractCombatHandler {
    private static final double WEAKEN_CHANCE = 0.75;
    private static final Random random = new Random();

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        int damage = 0;
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);

        // Второй игрок будет ослаблен, если сыграет шанс
        double chance = random.nextDouble();
        String message;
        if (chance < WEAKEN_CHANCE) {
            secondCombatant.setWeakenFor(firstCombatant.getLevel());
            message = String.format("%s ослаблен на %d хода!", secondCombatant.getName(), firstCombatant.getLevel());
        } else {
            message = String.format("%s защитился", secondCombatant.getName());
        }

        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
