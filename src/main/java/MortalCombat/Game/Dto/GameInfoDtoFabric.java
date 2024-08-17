package MortalCombat.Game.Dto;


import MortalCombat.Game.Game;
import MortalCombat.Game.GameState.Status;

public class GameInfoDtoFabric {
    public static GameInfoDto createGameInfoDto(String message, String alert, Status status) {
        GameInfoDto dto = createGameInfoDto(message, alert);
        dto.setStatus(status);
        return dto;
    }

    public static GameInfoDto createGameInfoDto(String message, String alert) {
        GameInfoDto dto = createGameInfoDto(message);
        dto.setAlert(alert);
        return dto;
    }

    public static GameInfoDto createGameInfoDto(String message, Status status) {
        GameInfoDto dto = createGameInfoDto(message);
        dto.setStatus(status);
        return dto;
    }

    public static GameInfoDto createGameInfoDto(String message) {
        GameInfoDto dto = new GameInfoDto();
        dto.setMessage(message);
        return dto;
    }

    public static GameInfoDto updateGameInfoDto(Game game, GameInfoDto dto) {
        if (dto == null) {
            return null;
        }
        dto.setPlayerSteps(game.isPlayerSteps());
        dto.setXP(game.getXP());
        dto.setRequiredXP(game.getRequiredXP());
        dto.setScore(game.getScore());
        dto.setEnemiesLeft(game.getEnemiesLeft());
        dto.setCurrentLocation(game.getCurrentLocation());
        dto.setTotalLocations(game.getTotalLocations());
        return dto;
    }

}
