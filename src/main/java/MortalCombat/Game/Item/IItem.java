package MortalCombat.Game.Item;

import MortalCombat.Game.Combatant.Player;

import java.util.Random;

public interface IItem {
    Random random = new Random();

    String getIconPath();
    String getName();
    boolean isActive();
    boolean tryToGet(boolean isBossKilled);
    void useBy(Player player);
    int getItemId();
}
