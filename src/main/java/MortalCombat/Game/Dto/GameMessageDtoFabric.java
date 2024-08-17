package MortalCombat.Game.Dto;

import MortalCombat.Game.Game;


public class GameMessageDtoFabric {

    public GameMessageDto createGameMessageDto(Game game, GameInfoDto gameInfo) {
        GameMessageDto gameMessageDto = new GameMessageDto();

        GameInfoDtoFabric.updateGameInfoDto(game, gameInfo);
        CombatantDto player = CombatantDtoFabric.createCombatantDto(game.getPlayer());
        CombatantDto enemy = CombatantDtoFabric.createCombatantDto(game.getEnemy());

        gameMessageDto.setGameInfo(gameInfo);
        gameMessageDto.setPlayer(player);
        gameMessageDto.setEnemy(enemy);

        return gameMessageDto;
    }
}
