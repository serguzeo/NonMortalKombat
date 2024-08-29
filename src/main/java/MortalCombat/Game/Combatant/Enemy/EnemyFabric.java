package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Enemy.Character.Character;
import MortalCombat.Game.Combatant.Enemy.Character.CharacterManager;
import MortalCombat.Game.Combatant.Enemy.EnemyType.*;
import MortalCombat.Game.Game;

import java.util.List;
import java.util.Random;

/**
 * Фабрика для создания врагов в игре.
 * Обеспечивает создание обычных врагов и боссов с учетом текущего состояния игры.
 */
public class EnemyFabric {

    /**
     * Список классов типов врагов, доступных для создания.
     */
    private final List<Class<? extends EnemyType>> enemyTypes = List.of(Fighter.class, Soldier.class, Tank.class, Wizard.class);

    /**
     * Менеджер, отвечающий за управление персонажами.
     */
    private final CharacterManager characterManager = new CharacterManager();
    private final Random rand = new Random();

    /**
     * Создает обычного врага.
     * Выбирает случайный тип врага и случайного персонажа для этого врага.
     * Уровень врага устанавливается на основе текущего уровня локации в игре.
     *
     * @param game Игра, для которой создается враг. Используется для получения уровня локации.
     * @return Новый экземпляр {@link Enemy}, представляющий обычного врага.
     * @throws RuntimeException Если возникла ошибка при создании экземпляров {@link EnemyType} или {@link Character}.
     */
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
            EnemyType enemyType = enemyClass.getDeclaredConstructor().newInstance();
            Character character = characterClass.getDeclaredConstructor().newInstance();
            int enemyLevel = game.getCurrentLocation(); // уровень врага соответствует номеру локации

            return new Enemy(enemyLevel, enemyType, character);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании экземпляра EnemyType", e);
        }
    }

    /**
     * Создает босса для текущего состояния игры.
     * Выбирает случайного персонажа для босса, затем создает экземпляр босса.
     *
     * @param game Текущая игра, для получения уровня врага и здоровья игрока.
     * @return Созданный босс.
     * @throws RuntimeException Если произошла ошибка при создании экземпляра {@link EnemyType}.
     */
    public Enemy createBoss(Game game) {
        // выбираем случайного босса
        List<Class<? extends Character>> characters = characterManager.getCharacterClasses(Boss.class);
        int characterIndex = rand.nextInt(characters.size());
        Class<? extends Character> characterClass = characters.get(characterIndex);

        // создаем врага
        try {
            Character character = characterClass.getDeclaredConstructor().newInstance();
            int enemyLevel = game.getCurrentLocation(); // уровень врага соответствует номеру локации

            return new Enemy(enemyLevel, new Boss(game.getPlayer().getMaxHP()), character);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании экземпляра EnemyType", e);
        }
    }
}
