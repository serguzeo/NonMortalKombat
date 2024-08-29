package MortalCombat.Game.Item;

import MortalCombat.Game.Combatant.Player;

import java.util.Random;

/**
 * Контракт для всех предметов в игре.
 */
public interface IItem {
    Random random = new Random();

    /**
     * Возвращает путь к иконке предмета.
     *
     * @return путь к иконке предмета.
     */
    String getIconPath();

    /**
     * Возвращает имя предмета.
     *
     * @return имя предмета.
     */
    String getName();

    /**
     * Проверяет, активен ли предмет.
     *
     * @return true, если предмет активен, иначе false.
     */
    boolean isActive();

    /**
     * Попытка получить предмет в зависимости от того, был ли убит босс.
     *
     * @param isBossKilled true, если босс был убит, иначе false.
     * @return true, если получение предмета успешно, иначе false.
     */
    boolean tryToGet(boolean isBossKilled);

    /**
     * Использует предмет на игроке.
     *
     * @param player игрок, который использует предмет.
     */
    void useBy(Player player);

    /**
     * Возвращает уникальный идентификатор предмета.
     *
     * @return уникальный идентификатор предмета.
     */
    int getItemId();
}
