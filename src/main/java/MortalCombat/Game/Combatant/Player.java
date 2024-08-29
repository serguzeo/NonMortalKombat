package MortalCombat.Game.Combatant;

import lombok.Setter;

/**
 * Класс, представляющий игрока в игре.
 * Наследует от {@link Combatant} и добавляет возможность установки и получения действия игрока.
 */
public class Player extends Combatant {
    @Setter
    private CombatantAction action;

    /**
     * Возвращает действие, которое игрок собирается выполнить.
     * Реализует абстрактный метод {@link Combatant#getAction()}.
     *
     * @return Действие, которое должен выполнить игрок.
     */
    @Override
    public CombatantAction getAction() {
        return action;
    }

    /**
     * Конструктор, создающий игрока с заданным именем и иконкой.
     * Инициализирует характеристики игрока значениями по умолчанию.
     *
     * @param playerName Имя игрока.
     * @param playerIconPath Путь к иконке игрока.
     */
    public Player(String playerName, String playerIconPath) {
        setHP(70); // Устанавливает текущее количество очков здоровья (HP) игрока.
        setMaxHP(70); // Устанавливает максимальное количество очков здоровья (HP) игрока.
        setDamage(11); // Устанавливает значение урона, наносимого игроком.

        setName(playerName); // Устанавливает имя игрока.
        setIconPath(playerIconPath); // Устанавливает путь к иконке игрока.
    }
}
