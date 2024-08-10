package MortalCombat.Game.Fabric;

import MortalCombat.Game.Combatant.Player;
import MortalCombat.Game.Dto.GameMessageDto;
import MortalCombat.Game.Game;

public class GameMessageDtoFabric {

    public GameMessageDto createGameMessageDto(Game game) {
        GameMessageDto gameMessageDto = new GameMessageDto();

        // player info
        Player player = game.getPlayer();
        gameMessageDto.setPlayerName(player.getName());
        gameMessageDto.setPlayerIconPath(player.getIconPath());
        int playerHealth = player.getHP() / player.getMaxHP() * 100;
        gameMessageDto.setPlayerProgressBarValue(playerHealth);
        gameMessageDto.setPlayerProgressBarString(player.getHP() + " / " + player.getMaxHP());
        gameMessageDto.setPlayerLevel(player.getLevel() + " LVL");
        gameMessageDto.setPlayerDamage("Урон: " + player.getDamage());

        return gameMessageDto;
    }
}
