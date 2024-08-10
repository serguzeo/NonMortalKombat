package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Enemy.Character.Character;
import MortalCombat.Game.Combatant.Enemy.Character.CharacterManager;
import MortalCombat.Game.Combatant.Enemy.EnemyType.*;
import MortalCombat.Game.Game;

import java.util.List;
import java.util.Random;

public class EnemyFabric {

    private final List<Class<? extends EnemyType>> enemyTypes = List.of(Fighter.class, Soldier.class, Tank.class, Wizard.class);
    private final CharacterManager characterManager = new CharacterManager();
    private final Random rand = new Random();

    public Enemy createCommonEnemy(Game game) {
        // выбираем случайный тип врага
        int typeIndex = rand.nextInt(enemyTypes.size());
        Class<? extends EnemyType> enemyClass = enemyTypes.get(typeIndex);

        // выбираем случайного персонажа
        List<Class<? extends Character>> characters = characterManager.getCharacterClasses(enemyClass);
        int characterIndex = rand.nextInt(characters.size());
        Class<? extends Character> characterClass = characters.get(characterIndex);

        // создаем врага
        try {
            EnemyType enemyInstance = enemyClass.getDeclaredConstructor().newInstance();
            Character character = characterClass.getDeclaredConstructor().newInstance();
            return new Enemy(game.getCurrentLocation(), enemyInstance, character);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании экземпляра EnemyType", e);
        }
    }
}
