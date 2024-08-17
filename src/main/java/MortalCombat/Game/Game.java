package MortalCombat.Game;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Enemy.EnemyFabric;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Dto.GameInfoDto;
import MortalCombat.Game.Dto.GameInfoDtoFabric;
import MortalCombat.Game.Dto.GameMessageDto;
import MortalCombat.Game.Dto.GameMessageDtoFabric;
import MortalCombat.Game.GameState.Status;
import MortalCombat.Game.GameState.StepState;
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
    private boolean playerSteps = true;

    private final GameMessageDtoFabric gameMessageDtoFabric = new GameMessageDtoFabric();
    private final EnemyFabric enemyFabric = new EnemyFabric();
    private final CombatHandlerRegistry combatHandlerRegistry = new CombatHandlerRegistry();


    public GameMessageDto startGame(Integer locationNumber, String playerName, String playerIconPath) {
        totalLocations = locationNumber;

        player = new Player(playerName, playerIconPath);
        enemy = enemyFabric.createCommonEnemy(this);

        return gameMessageDtoFabric.createGameMessageDto(
                this, GameInfoDtoFabric.createGameInfoDto(Constants.START_GAME_MESSAGE)
        );
    }

    public GameMessageDto processUserAction(CombatantAction combatantAction) {
        // отдаем ход
        setPlayerSteps(!isPlayerSteps());

        // регистрируем ход игрока
        stepState.addStep(player, combatantAction);

        // если это ход игрока, то добавляем ответ врага и следующий ход врага
        if (stepState.getSize() < 2) {
            stepState.addStep(enemy, enemy.getAction());
            stepState.addStep(enemy, enemy.getAction());
        }

        // обрабатываем ход
        CombatHandler handler = combatHandlerRegistry.getHandler(stepState);
        String message = handler.handle(stepState);

        // подводим итоги хода
        String alert = null;
        Status status = null;
        if (enemy.getHP() <= 0) {
            Enemy defeatedEnemy = enemy;
            status = handleDeadEnemy(defeatedEnemy);

            message = Constants.YOUR_STEP_MESSAGE;
            alert = String.format("<html>Враг %s побежден!</html>", defeatedEnemy.getName());
        } else if (player.getHP() <= 0) {
            status = Status.LOSE;
        }

        return gameMessageDtoFabric.createGameMessageDto(
                this, GameInfoDtoFabric.createGameInfoDto(message, alert, status)
        );
    }

    public GameMessageDto upPlayerHealth() {
        int newHealth = (int) ((float) player.getMaxHP() * 1.5);
        player.setHP(newHealth);
        player.setMaxHP(newHealth);
        return gameMessageDtoFabric.createGameMessageDto(this, new GameInfoDto());
    }

    public GameMessageDto upPlayerDamage() {
        int newDamage = (int) ((float) player.getDamage() * 1.75);
        player.setDamage(newDamage);
        return gameMessageDtoFabric.createGameMessageDto(this, new GameInfoDto());
    }

    private Status handleDeadEnemy(Enemy defeatedEnemy) {
        Status status = Status.ALERT;

        // добавляем очки за победу
        score += calculatePointsForKill(defeatedEnemy.getLevel(), player.getHP(), player.getMaxHP());

        // заканчиваем игру, если победа
        if (getCurrentLocation() == totalLocations && enemiesLeft == 0) {
            status = Status.JUST_VICTORY;

            // логика с рейтингом


            return status;
        }

        // очищаем текущую последовательность действий
        stepState.clear();
        setPlayerSteps(true);

        // обновляем XP, если достаточно XP, то переводим на новый уровень
        int xp = calculateXPForKill();
        XP += xp;
        if (XP >= requiredXP) {
            player.setLevel(player.getLevel() + 1);
            updateRequiredXP(requiredXP);
            status = Status.LEVEL_UP;
        }

        // если врагов не осталось, переводим на новую локацию
        if (enemiesLeft == 0) {
            currentLocation++;
            // +1 тк.к. после создания первого врага отнимется единица
            enemiesLeft = this.calculateNewEnemiesLeft(currentLocation) + 1;
        }

        // создаем нового врага или босса
        if (enemiesLeft > 1) {
            enemy = enemyFabric.createCommonEnemy(this);
        } else {
            enemy = enemyFabric.createBoss(this);
        }
        enemiesLeft--;

        // восстанавливаем здоровье
        player.setHP(player.getMaxHP());
        return status;
    }

    private int calculateXPForKill() {
        return 17;
    }

    private int calculatePointsForKill(int enemyLevel, int playerHP, int playerMaxHP) {
        return (int) (25f * enemyLevel * playerHP / playerMaxHP);
    }

    private int calculateNewEnemiesLeft(int locationNumber) {
        return locationNumber + 2;
    }


    private void updateRequiredXP(int oldRequiredXP) {
        requiredXP = (int) (oldRequiredXP * 2.25);
    }


}
