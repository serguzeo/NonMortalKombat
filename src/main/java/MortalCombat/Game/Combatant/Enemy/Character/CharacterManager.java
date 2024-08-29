package MortalCombat.Game.Combatant.Enemy.Character;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;
import org.reflections.Reflections;

/**
 * Менеджер для управления персонажами врагов.
 * Обеспечивает получение классов персонажей, соответствующих заданному типу врага.
 */
public class CharacterManager {

    /**
     * Возвращает список классов персонажей, соответствующих заданному типу врага.
     * Использует имя типа врага для выбора пакета, содержащего классы персонажей.
     *
     * @param enemyClass Класс типа врага, для которого нужно получить соответствующих персонажей.
     * @return Список классов персонажей, соответствующих заданному типу врага.
     * @throws IllegalArgumentException Если предоставленный тип врага неизвестен.
     */
    public List<Class<? extends Character>> getCharacterClasses(Class<? extends EnemyType> enemyClass) {
        return switch (enemyClass.getSimpleName()) {
            case "Fighter" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Fighter");
            case "Soldier" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Soldier");
            case "Tank" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Tank");
            case "Wizard" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Wizard");
            case "Boss" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Boss");
            default -> throw new IllegalArgumentException("Unknown enemy class: " + enemyClass.getName());
        };
    }

    /**
     * Получает все классы персонажей из указанного пакета.
     * Использует библиотеку Reflections для поиска всех подтипов {@link Character} в указанном пакете.
     *
     * @param packageName Имя пакета, из которого нужно получить классы персонажей.
     * @return Список классов персонажей из указанного пакета.
     */
    private List<Class<? extends Character>> getClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Character>> classes = reflections.getSubTypesOf(Character.class);
        return new ArrayList<>(classes);
    }
}
