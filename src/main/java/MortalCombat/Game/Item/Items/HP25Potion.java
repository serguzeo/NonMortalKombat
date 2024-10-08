package MortalCombat.Game.Item.Items;

import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Item.IItem;
import lombok.Getter;

/**
 * Представляет собой зелье здоровья, восстанавливающее 25% от максимального здоровья игрока.
 */
public class HP25Potion implements IItem {
    @Getter
    private final int itemId;
    private static final double CHANCE = 0.25;

    public HP25Potion() {
        this.itemId = 1;
    }

    @Override
    public String getIconPath() {
        return "img/items/hp25.jpg";
    }

    @Override
    public String getName() {
        return "Малое зелье (+25%)";
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean tryToGet(boolean isBossKilled) {
        double chance = random.nextDouble();
        return chance < CHANCE + (isBossKilled ? 0.5 : 0);
    }

    /**
     * Применяет эффект зелья к указанному игроку. Увеличивает здоровье игрока на 25% от его максимального здоровья,
     * не превышая при этом максимальное здоровье игрока.
     *
     * @param player игрок, использующий зелье
     */
    @Override
    public void useBy(Player player) {
        player.setHP(Math.min(player.getHP() + player.getMaxHP() / 4, player.getMaxHP()));
    }
}
