package MortalCombat.Game.Combatant;

import lombok.Data;

/**
 * Абстрактный класс, представляющий боевого персонажа.
 * Хранит основные характеристики персонажа и предоставляет методы для управления ими.
 */
@Data
public abstract class Combatant {
    private String name = "Combatant";
    private String iconPath;

    private int level = 1;
    private int HP = 80;
    private int maxHP = 80;

    private int damage = 16;

    private boolean isStunned = false;
    private int weakenFor = 0;

    /**
     * Возвращает действие, которое должен выполнить персонаж.
     * Этот метод должен быть реализован в подклассах.
     *
     * @return Действие, которое должен выполнить персонаж.
     */
    public abstract CombatantAction getAction();

    /**
     * Получает урон, уменьшая количество очков здоровья (HP) персонажа.
     * Если количество HP становится меньше нуля, оно не изменяется.
     *
     * @param damage Количество урона, который нужно нанести.
     */
    public void takeDamage(int damage) {
        setHP(getHP() - damage);
    }
}
