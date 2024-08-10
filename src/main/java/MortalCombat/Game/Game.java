package MortalCombat.Game;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Enemy.EnemyFabric;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Dto.GameMessageDto;
import MortalCombat.Game.Dto.GameMessageDtoFabric;
import MortalCombat.Game.Handler.CombatHandler;
import MortalCombat.Game.Handler.CombatHandlerRegistry;
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

    private StepState stepState = new StepState();

    private final GameMessageDtoFabric gameMessageDtoFabric = new GameMessageDtoFabric();
    private final EnemyFabric enemyFabric = new EnemyFabric();
    private final CombatHandlerRegistry combatHandlerRegistry = new CombatHandlerRegistry();


    public GameMessageDto startGame(Integer locationNumber, String playerName, String playerIconPath) {
        totalLocations = locationNumber;

        player = new Player(playerName, playerIconPath);
        enemy = enemyFabric.createCommonEnemy(this);

        return gameMessageDtoFabric.createGameMessageDto(
                this,
                "<html>" +
                        "Да начнется великая битва!" +
                        "<br>" +
                        "<div style='text-align:center;'>Ваш ход!</div>" +
                        "</html>"
        );
    }

    public GameMessageDto processUserAction(CombatantAction combatantAction) {

        stepState.addStep(player, combatantAction);

        if (stepState.getSize() < 2) {
            stepState.addStep(enemy, enemy.getAction());
            stepState.addStep(enemy, enemy.getAction());
        }

        CombatHandler handler = combatHandlerRegistry.getHandler(stepState);
        handler.handle(stepState);

        return gameMessageDtoFabric.createGameMessageDto(this, "четапроизошло");
    }


}
