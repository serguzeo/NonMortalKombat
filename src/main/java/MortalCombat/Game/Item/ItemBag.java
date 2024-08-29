package MortalCombat.Game.Item;

import MortalCombat.Game.Item.Items.HP25Potion;
import MortalCombat.Game.Item.Items.HP50Potion;
import MortalCombat.Game.Item.Items.RenaissanceCross;

import java.util.*;

/**
 * Класс представляет собой сумку предметов игрока.
 */
public class ItemBag {
    private final Map<IItem, Integer> itemBag = new HashMap<>();

    /**
     * Конструктор, инициализирующий сумку предметов с набором известных предметов.
     * Каждого из них изначально 0 штук.
     */
    public ItemBag() {
        itemBag.put(new RenaissanceCross(), 0);
        itemBag.put(new HP25Potion(), 0);
        itemBag.put(new HP50Potion(), 0);
    }

    /**
     * Возвращает карту, представляющую сумку предметов, где ключами являются предметы,
     * а значениями - их количество.
     *
     * @return карта предметов и их количества
     */
    public Map<IItem, Integer> getItemBag() {
        return itemBag;
    }

    /**
     * Находит предмет по его уникальному идентификатору.
     *
     * @param itemId уникальный идентификатор предмета
     * @return предмет с заданным идентификатором, или null, если предмет не найден
     */
    public IItem getItemById(Integer itemId) {
        for (IItem item : itemBag.keySet()) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    /**
     * Попытка получения предметов после убийства босса.
     * Если босс был убит, шанс на получение предмета увеличивается, что может привести
     * к увеличению количества каждого предмета в сумке.
     *
     * @param isBossKilled true, если босс был убит, false в противном случае
     */
    public void tryToGetItemsForKill(boolean isBossKilled) {
        itemBag.replaceAll((i, v) -> itemBag.get(i) + (i.tryToGet(isBossKilled) ? 1 : 0));
    }
}
