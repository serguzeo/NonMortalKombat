package MortalCombat.Game.Item.Items;

import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Item.IItem;
import lombok.Getter;

public class HP50Potion implements IItem {
    @Getter
    private final int itemId;
    private static final double CHANCE = 0.15;

    public HP50Potion() {
        this.itemId = 2;
    }

    @Override
    public String getIconPath() {
        return "img/items/hp50.jpg";
    }

    @Override
    public String getName() {
        return "Среднее зелье (+50%)";
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

    @Override
    public void useBy(Player player) {
        player.setHP(Math.min(player.getHP() + player.getMaxHP() / 2, player.getMaxHP()));
    }
}
