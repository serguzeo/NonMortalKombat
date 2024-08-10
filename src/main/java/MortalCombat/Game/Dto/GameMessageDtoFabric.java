package MortalCombat.Game.Dto;

import MortalCombat.Game.Combatant.Enemy.Enemy;
import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Game;

public class GameMessageDtoFabric {

    public GameMessageDto createGameMessageDto(Game game, String message) {
        GameMessageDto gameMessageDto = new GameMessageDto();

        // game info
        gameMessageDto.setScore(String.valueOf(game.getScore()));
        gameMessageDto.setXP(game.getXP() + "/" + game.getRequiredXP());
        gameMessageDto.setLocation("Локация: " + game.getCurrentLocation() + "/" + game.getTotalLocations());
        gameMessageDto.setEnemiesLeft("Осталось противников: " + game.getEnemiesLeft());
        gameMessageDto.setMessage(message);

        // player info
        Player player = game.getPlayer();
        gameMessageDto.setPlayerName(player.getName());
        gameMessageDto.setPlayerIconPath(player.getIconPath());

        int playerHealth = Math.round((float) player.getHP() / (float) player.getMaxHP() * 100);
        gameMessageDto.setPlayerProgressBarValue(playerHealth);
        gameMessageDto.setPlayerProgressBarString(player.getHP() + " / " + player.getMaxHP());
        gameMessageDto.setPlayerLevel(player.getLevel() + " LVL");
        gameMessageDto.setPlayerDamage("Урон: " + player.getDamage());

        // enemy info
        Enemy enemy = game.getEnemy();
        gameMessageDto.setEnemyName(enemy.getName());
        gameMessageDto.setEnemyIconPath(enemy.getIconPath());
        int enemyHealth = Math.round((float) enemy.getHP() / (float) enemy.getMaxHP() * 100);
        gameMessageDto.setEnemyProgressBarValue(enemyHealth);
        gameMessageDto.setEnemyProgressBarString(enemy.getHP() + " / " + enemy.getMaxHP());
        gameMessageDto.setEnemyLevel(enemy.getLevel() + " LVL");
        gameMessageDto.setEnemyDamage("Урон: " + enemy.getDamage());

        return gameMessageDto;
    }
}
