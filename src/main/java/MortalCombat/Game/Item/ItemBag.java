package MortalCombat.Game.Item;

import MortalCombat.Game.Item.Items.HP25Potion;
import MortalCombat.Game.Item.Items.HP50Potion;
import MortalCombat.Game.Item.Items.RenaissanceCross;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class ItemBag {
    private final Map<IItem, Integer> itemBag = new HashMap<>();

    public ItemBag() {
        itemBag.put(new RenaissanceCross(), 0);
        itemBag.put(new HP25Potion(), 0);
        itemBag.put(new HP50Potion(), 0);
    }

    public Map<IItem, Integer> getItemBag() {
        return itemBag;
    }

    public IItem getItemById(Integer itemId) {
        for (IItem item : itemBag.keySet()) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public void tryToGetItemsForKill(boolean isBossKilled) {
        itemBag.replaceAll((i, v) -> itemBag.get(i) + (i.tryToGet(isBossKilled) ? 1 : 0));
    }
}
