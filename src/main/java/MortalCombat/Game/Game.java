package MortalCombat.Game;

import MortalCombat.Game.Combatant.CombatantAction;
import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Enemy.EnemyFabric;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Dto.*;
import MortalCombat.Game.GameState.Status;
import MortalCombat.Game.GameState.StepState;
import MortalCombat.Game.ActionHandler.CombatHandler;
import MortalCombat.Game.ActionHandler.CombatHandlerRegistry;
import MortalCombat.Game.Item.IItem;
import MortalCombat.Game.Item.ItemBag;
import MortalCombat.Game.Rating.Rating;
import MortalCombat.Game.Rating.RatingTable;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Основной класс игры "Mortal Combat", который управляет логикой игры,
 * включая взаимодействие игрока с врагами, обработку действий и управление предметами.
 */
@Data
public class Game {
    private Player player;
    private int score = 0;
    private int XP = 0;
    private int requiredXP = 40;
    private ItemBag itemBag = new ItemBag();

    private Enemy enemy;
    private int enemiesLeft = 3;

    private int currentLocation = 1;
    private int totalLocations;

    private StepState stepState = new StepState();
    private boolean playerSteps = true;

    private final GameMessageDtoFabric gameMessageDtoFabric = new GameMessageDtoFabric();
    private final EnemyFabric enemyFabric = new EnemyFabric();
    private final CombatHandlerRegistry combatHandlerRegistry = new CombatHandlerRegistry();
    private final RatingTable ratingTable = new RatingTable();

    /**
     * Инициализирует новую игру с заданным количеством локаций и именем игрока.
     *
     * @param locationNumber Количество локаций в игре.
     * @param playerName Имя игрока.
     * @param playerIconPath Путь к иконке игрока.
     * @return GameMessageDto объект с информацией о начале игры.
     */
    public GameMessageDto startGame(Integer locationNumber, String playerName, String playerIconPath) {
        totalLocations = locationNumber;

        player = new Player(playerName, playerIconPath);
        enemy = enemyFabric.createCommonEnemy(this);

        return gameMessageDtoFabric.createGameMessageDto(
                this, GameInfoDtoFabric.createGameInfoDto(Constants.START_GAME_MESSAGE)
        );
    }

    /**
     * Обрабатывает действие пользователя, выполняемое в рамках текущего игрового шага.
     *
     * @param combatantAction Действие игрока.
     * @return GameMessageDto объект с информацией о результате действия.
     */
    public GameMessageDto processUserAction(CombatantAction combatantAction) {
        // отдаем ход
        setPlayerSteps(!isPlayerSteps());

        // регистрируем ход игрока
        // если это ход игрока, то добавляем ответ врага и следующий ход врага
        stepState.addStep(player, combatantAction);
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
            // пытаемся возродиться
            // у креста возрождения ID = 3
            IItem renaissanceCross = itemBag.getItemById(3);
            Map<IItem, Integer> curItemBag = itemBag.getItemBag();
            if (curItemBag.get(renaissanceCross) > 0) {
                player.setHP(0);
                curItemBag.put(renaissanceCross, curItemBag.get(renaissanceCross) - 1);
                renaissanceCross.useBy(player);
                status = Status.ALERT;
                alert = "Вас спас крест возрождения!";
            } else {
                status = Status.LOSE;
            }
        }

        return gameMessageDtoFabric.createGameMessageDto(
                this, GameInfoDtoFabric.createGameInfoDto(message, alert, status)
        );
    }

    /**
     * Возвращает список предметов, находящихся в инвентаре игрока.
     *
     * @return Список объектов ItemDto, представляющих предметы в инвентаре.
     */
    public List<ItemDto> getItemBag() {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (IItem item: itemBag.getItemBag().keySet()) {
            itemDtos.add(ItemDtoFabric.createItemDto(item, itemBag.getItemBag().get(item)));
        }
        return itemDtos;
    }

    /**
     * Обрабатывает использование предмета игроком по его идентификатору.
     *
     * @param itemId Идентификатор предмета, который нужно использовать.
     * @return GameMessageDto объект с информацией о результате использования предмета.
     */
    public GameMessageDto processItemUsage(int itemId) {
        IItem item = itemBag.getItemById(itemId);

        if (item != null) {
            useItem(item);
        }
        return gameMessageDtoFabric.createGameMessageDto(this, new GameInfoDto());
    }

    /**
     * Увеличивает максимальное здоровье игрока и восстанавливает его.
     *
     * @return GameMessageDto объект с информацией о новом состоянии игрока.
     */
    public GameMessageDto upPlayerHealth() {
        int newHealth = (int) ((float) player.getMaxHP() * 1.5);
        player.setHP(newHealth);
        player.setMaxHP(newHealth);
        return gameMessageDtoFabric.createGameMessageDto(this, new GameInfoDto());
    }

    /**
     * Увеличивает урон игрока.
     *
     * @return GameMessageDto объект с информацией о новом состоянии игрока.
     */
    public GameMessageDto upPlayerDamage() {
        int newDamage = (int) ((float) player.getDamage() * 1.5);
        player.setDamage(newDamage);
        return gameMessageDtoFabric.createGameMessageDto(this, new GameInfoDto());
    }

    /**
     * Пытается получить новый предмет после победы над врагом.
     */
    private void obtainNewItem() {
        itemBag.tryToGetItemsForKill(enemiesLeft == 0);
    }

    /**
     * Использует предмет из инвентаря игрока.
     *
     * @param item Предмет, который нужно использовать.
     */
    private void useItem(IItem item) {
        if (itemBag.getItemBag().get(item) > 0) {
            item.useBy(player);
            itemBag.getItemBag().put(item, itemBag.getItemBag().get(item) - 1);
        }
    }

    /**
     * Обрабатывает поражение врага, обновляет состояние игры, начисляет очки и опыт.
     *
     * @param defeatedEnemy Объект врага, который был побежден.
     * @return Статус игры после победы над врагом.
     */
    private Status handleDeadEnemy(Enemy defeatedEnemy) {
        Status status = Status.ALERT;

        // добавляем очки за победу
        score += calculatePointsForKill(defeatedEnemy.getLevel(), player.getHP(), player.getMaxHP());

        // заканчиваем игру, если победа
        if (getCurrentLocation() == totalLocations && enemiesLeft == 0) {
            status = Status.JUST_VICTORY;

            // в топ 10?
            ratingTable.importRatings();
            int place = ratingTable.registerRating(new Rating(player.getName(), score, LocalDate.now()));
            if (place >= 1 && place <= 10) {
                return Status.TOP_VICTORY;
            }

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

        // если врагов не осталось, переводим на новую локацию (БОСС ПОБЕЖДЕН)
        if (enemiesLeft == 0) {
            currentLocation++;
            // +1 тк.к. после создания первого врага отнимется единица
            enemiesLeft = this.calculateNewEnemiesLeft(currentLocation) + 1;
        }

        // пробуем получить предмет
        obtainNewItem();

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

    /**
     * Рассчитывает количество очков опыта (XP) за убийство врага.
     *
     * @return Количество XP за убийство врага.
     */
    private int calculateXPForKill() {
        return 17;
    }

    /**
     * Рассчитывает количество очков за убийство врага на основе его уровня и текущего здоровья игрока.
     *
     * @param enemyLevel Уровень врага.
     * @param playerHP Текущее здоровье игрока.
     * @param playerMaxHP Максимальное здоровье игрока.
     * @return Количество очков за убийство врага.
     */
    private int calculatePointsForKill(int enemyLevel, int playerHP, int playerMaxHP) {
        return (int) (25f * enemyLevel * playerHP / playerMaxHP);
    }

    /**
     * Рассчитывает количество врагов в новой локации на основе номера локации.
     *
     * @param locationNumber Номер текущей локации.
     * @return Количество врагов в новой локации.
     */
    private int calculateNewEnemiesLeft(int locationNumber) {
        return locationNumber + 2;
    }

    /**
     * Обновляет требуемое количество опыта (XP) для повышения уровня.
     *
     * @param oldRequiredXP Предыдущее значение требуемого XP.
     */
    private void updateRequiredXP(int oldRequiredXP) {
        requiredXP = (int) (oldRequiredXP * 2.25);
    }


}
