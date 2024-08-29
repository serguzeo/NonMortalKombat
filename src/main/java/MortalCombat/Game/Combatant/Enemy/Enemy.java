package MortalCombat.Game.Combatant.Enemy;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.Character.Character;
import MortalCombat.Game.Combatant.Enemy.EnemyType.EnemyType;
import lombok.Setter;

/**
 * Класс, представляющий врага в игре.
 * Наследует от {@link Combatant} и включает тип врага и характеристику персонажа.
 */
public class Enemy extends Combatant {
    /**
     * Тип врага, определяющий его поведение и характеристики.
     */
    @Setter
    private EnemyType enemyType;

    /**
     * Характеристика персонажа врага, включая имя и иконку.
     */
    private final Character character;

    /**
     * Конструктор, создающий врага с заданным уровнем, типом и характеристикой персонажа.
     *
     * @param level Уровень врага.
     * @param enemyType Тип врага, определяющий его поведение и характеристики.
     * @param character Характеристика персонажа врага, включая имя и иконку.
     */
    public Enemy(int level, EnemyType enemyType, Character character) {
        setLevel(level); // Устанавливает уровень врага.
        setEnemyType(enemyType); // Устанавливает тип врага.
        setHP(getMaxHP()); // Устанавливает текущее количество очков здоровья (HP) врага равным максимальному HP.
        setMaxHP(getMaxHP()); // Устанавливает максимальное количество очков здоровья (HP) врага.
        this.character = character; // Устанавливает характеристику персонажа врага.
    }

    /**
     * Возвращает действие, которое враг собирается выполнить.
     * Реализует абстрактный метод {@link Combatant#getAction()}.
     *
     * @return Действие, которое враг должен выполнить.
     */
    @Override
    public CombatantAction getAction() {
        return enemyType.getAction();
    }

    /**
     * Возвращает максимальное количество очков здоровья (HP) врага, с учетом его уровня.
     * Значение рассчитывается на основе базового HP типа врага и уровня врага.
     *
     * @return Максимальное количество HP врага.
     */
    @Override
    public int getMaxHP() {
        return (int) (enemyType.getBaseHP() * (1 + 0.25 * (getLevel() - 1)));
    }

    /**
     * Возвращает урон, наносимый врагом, с учетом его уровня.
     * Значение рассчитывается на основе базового урона типа врага и уровня врага.
     *
     * @return Урон, наносимый врагом.
     */
    @Override
    public int getDamage() {
        return (int) (enemyType.getBaseDamage() * (1 + 0.32 * (getLevel() - 1)));
    }

    /**
     * Возвращает имя врага, состоящее из имени его характеристики и типа врага.
     *
     * @return Имя врага.
     */
    @Override
    public String getName() {
        return character.getName() + " (" + enemyType.toString() + ")";
    }

    /**
     * Возвращает путь к иконке врага.
     *
     * @return Путь к иконке врага.
     */
    @Override
    public String getIconPath() {
        return character.getIconPath();
    }
}
