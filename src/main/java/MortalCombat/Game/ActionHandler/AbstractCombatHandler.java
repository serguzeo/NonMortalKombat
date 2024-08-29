package MortalCombat.Game.ActionHandler;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.GameState.StepState;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Абстрактный класс для обработки боевых действий.
 * Предоставляет базовую реализацию для обработки боевых действий.
 */
public abstract class AbstractCombatHandler implements CombatHandler {

    @Override
    public String handle(StepState state) {
        Pair<Combatant, CombatantAction> first = state.getNextStep();
        Pair<Combatant, CombatantAction> second = state.getNextStep();
        Combatant firstCombatant = first.getKey();
        Combatant secondCombatant = second.getKey();

        // Обработка стан состояния
        String stunHandleResult = handleWithStun(firstCombatant, secondCombatant, first.getValue(), second.getValue());
        if (stunHandleResult != null) {
            return stunHandleResult;
        }

        // Выполнение специфичной логики
        return handleSpecific(firstCombatant, secondCombatant, first.getValue(), second.getValue());
    }

    protected abstract String handleSpecific(Combatant firstCombatant, Combatant secondCombatant,
                                             CombatantAction firstAction, CombatantAction secondAction);
}
