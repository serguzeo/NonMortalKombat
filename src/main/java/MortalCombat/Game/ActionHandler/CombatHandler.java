package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.GameState.StepState;

public interface CombatHandler {
    String handle(StepState state);

    default String handleWithStun(
            Combatant firstCombatant, Combatant secondCombatant,
            CombatantAction firstCombatantAction, CombatantAction secondCombatantAction
    ) {

        Combatant attacker;
        Combatant target;

        if (firstCombatant.isStunned() && secondCombatant.isStunned()) {
            firstCombatant.setStunned(false);
            secondCombatant.setStunned(false);
            return  "<html>Передышка... Оба в стане...</html>";
        } else if (secondCombatant.isStunned()) {
            attacker = firstCombatant;
            target = secondCombatant;

            return handleActionOnStunned(attacker, target, firstCombatantAction);
        } else if (firstCombatant.isStunned()) {
            attacker = secondCombatant;
            target = firstCombatant;

            return handleActionOnStunned(attacker, target, secondCombatantAction);
        } else {
            return null;
        }
    }

    default int handleWeakness(Combatant attacker, Combatant target, int damage) {
        if (attacker.getWeakenFor() > 0 ) {
            attacker.setWeakenFor(attacker.getWeakenFor() - 1);
            damage = damage / 2;
        }

        if (target.getWeakenFor() > 0 ) {
            target.setWeakenFor(target.getWeakenFor() - 1);
            damage = (int) (((float) damage) * 1.25);
        }

        return damage;
    }

    default String handleActionOnStunned(Combatant attacker, Combatant target, CombatantAction action) {
        int damage = attacker.getDamage();
        damage = handleWeakness(attacker, target, damage);

        String message;
        if (action == CombatantAction.ATTACK) {
            target.takeDamage(damage);

            message = String.format("%s наносит удар!", attacker.getName());

        } else if (action == CombatantAction.DEFEND) {
            message = String.format("%s защищается!", attacker.getName());
        } else if (action == CombatantAction.WEAKEN) {
            target.setWeakenFor(attacker.getLevel());
            message = String.format("%s ослаблен на %d хода!", target.getName(), attacker.getLevel());
        } else if (action == CombatantAction.REGENERATE) {
            int new_hp = attacker.getHP() + (attacker.getMaxHP() - attacker.getHP()) / 2;
            attacker.setHP(new_hp);
            message = String.format("%s восстанавливает %dHP", attacker.getName(), (attacker.getMaxHP() - attacker.getHP()) / 2);
        } else {
            message = "неизвестность...";
        }

        target.setStunned(false);
        return String.format("<html>%s<br>-%dHP %s</html>", message, damage, target.getName());
    }
}
