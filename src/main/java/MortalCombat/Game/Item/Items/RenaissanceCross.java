package MortalCombat.Game.Item.Items;

import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Item.IItem;
import lombok.Getter;

/**
 * Представляет собой крест возрождения, который восстанавливает 5% от максимального здоровья игрока.
 */
public class RenaissanceCross implements IItem {
    @Getter
    private final int itemId;
    private static final double CHANCE = 0.05;

    public RenaissanceCross() {
        this.itemId = 3;
    }

    @Override
    public String getIconPath() {
        return "img/items/cross.png";
    }

    @Override
    public String getName() {
        return "Крест возрождения";
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean tryToGet(boolean isBossKilled) {
        double chance = random.nextDouble();
        return chance < CHANCE + (isBossKilled ? 0.5 : 0);
    }

    /**
     * Применяет эффект предмета к указанному игроку.
     * Восстанавливает здоровье игрока на 5% от его максимального здоровья.
     *
     * @param player игрок, использующий предмет
     */
    @Override
    public void useBy(Player player) {
        player.setHP((int) ((float) player.getMaxHP() * 0.05));
    }
}
