package MortalCombat.Game.Handler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;

import java.util.Random;

import static MortalCombat.Game.Constants.HANDLE_MESSAGE;

public class DefendDefendHandler extends AbstractCombatHandler {
    private static final Random random = new Random();
    private static final double STUN_CHANCE = 0.5;

    @Override
    protected String handleSpecific(Combatant firstCombatant, Combatant secondCombatant, CombatantAction firstAction, CombatantAction secondAction) {
        int damage = 0;
        damage = handleWeakness(firstCombatant, secondCombatant, damage);
        secondCombatant.takeDamage(damage);

        // Второй игрок будет оглушен, если сыграет шанс
        double chance = random.nextDouble();
        String message;
        if (chance < STUN_CHANCE) {
            secondCombatant.setStunned(true);
            message = String.format("%s оглушает противника!", firstCombatant.getName());
        } else {
            message = "Оба игрока ушли в защиту...";
        }

        return String.format(HANDLE_MESSAGE, message, damage, secondCombatant.getName());
    }
}
