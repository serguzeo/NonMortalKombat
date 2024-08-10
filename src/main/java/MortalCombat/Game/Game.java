package MortalCombat.Game;

import MortalCombat.Game.Combatant.Combatant;
import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Dto.GameMessageDto;
import MortalCombat.Game.Fabric.GameMessageDtoFabric;
import MortalCombat.Game.Item.Item;
import lombok.Data;

import java.util.List;

@Data
public class Game {
    private Player player;
    private int score = 0;
    private int XP = 0;
    private int requiredXP = 40;
    private List<Item> inventory;

    private Enemy enemy;
    private int enemiesLeft = 3;

    private int currentLocation = 1;
    private int totalLocations;

    private final GameMessageDtoFabric gameMessageDtoFabric = new GameMessageDtoFabric();


    public GameMessageDto startGame(Integer locationNumber, String playerName, String playerIconPath) {
        totalLocations = locationNumber;

        player = new Player(playerName, playerIconPath);

        return gameMessageDtoFabric.createGameMessageDto(this);
    }
}
