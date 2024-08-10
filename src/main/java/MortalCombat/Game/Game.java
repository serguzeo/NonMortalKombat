package MortalCombat.Game;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Item.Item;
import lombok.Data;

import java.util.List;

@Data
public class Game {
    private Player player;
    private Enemy enemy;

    private int score;

    private int XP;
    private int requiredXP;

    private int currentLocation;
    private int totalLocations;

    private int enemiesLeft;

    private List<Item> inventory;
}
