package MortalCombat.Game.GameState;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;


/**
 * Класс для управления состоянием шага в боевой системе.
 * Хранит список боевых персонажей и их действий, а также позволяет управлять и извлекать информацию о шагах.
 */
public class StepState {
    private final LinkedList<Combatant> combatants = new LinkedList<>();
    private final LinkedList<CombatantAction> combatantActions = new LinkedList<>();

    /**
     * Добавляет новый шаг в состояние.
     * Сохраняет указанного персонажа и его действие.
     *
     * @param combatant       Персонаж, выполняющий действие.
     * @param combatantAction Действие персонажа.
     */
    public void addStep(Combatant combatant, CombatantAction combatantAction) {
        combatants.addLast(combatant);
        combatantActions.addLast(combatantAction);
    }

    /**
     * Извлекает и удаляет следующий шаг из состояния.
     * Возвращает пару, содержащую персонажа и его действие.
     *
     * @return Пара с персонажем и его действием, или null, если нет шагов.
     */
    public Pair<Combatant, CombatantAction> getNextStep() {
        return Pair.of(combatants.poll(), combatantActions.poll());
    }

    /**
     * Получает количество оставшихся шагов в состоянии.
     *
     * @return Количество шагов.
     */
    public int getSize() {
        return combatants.size();
    }

    /**
     * Получает строковое представление комбинации действий персонажей.
     * Формат: "Действие1-Действие2".
     *
     * @return Строка с комбинацией действий.
     */
    public String getCombination() {
        return combatantActions.get(0) + "-" + combatantActions.get(1);
    }

    /**
     * Очищает состояние, удаляя все сохраненные шаги.
     */
    public void clear() {
        combatants.clear();
        combatantActions.clear();
    }
}
