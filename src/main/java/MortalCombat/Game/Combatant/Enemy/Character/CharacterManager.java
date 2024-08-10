package MortalCombat.Game.Combatant.Enemy.Character;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;
import MortalCombat.Game.Combatant.Enemy.EnemyType.Fighter;
import org.reflections.Reflections;

public class CharacterManager {

    public List<Class<? extends Character>> getCharacterClasses(Class<? extends EnemyType> enemyClass) {
        return switch (enemyClass.getSimpleName()) {
            case "Fighter" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Fighter");
            case "Soldier" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Soldier");
            case "Tank" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Tank");
            case "Wizard" -> getClassesInPackage("MortalCombat.Game.Combatant.Enemy.Character.Wizard");
            default -> throw new IllegalArgumentException("Unknown enemy class: " + enemyClass.getName());
        };
    }

    private List<Class<? extends Character>> getClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends Character>> classes = reflections.getSubTypesOf(Character.class);
        return new ArrayList<>(classes);
    }
}
