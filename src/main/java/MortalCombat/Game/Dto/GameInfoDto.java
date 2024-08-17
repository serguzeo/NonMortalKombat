package MortalCombat.Game.Dto;

import MortalCombat.Game.GameState.Status;
import lombok.Data;

@Data
public class GameInfoDto {
    private boolean playerSteps;
    private int score;
    private int XP;
    private int requiredXP;
    private int currentLocation;
    private int totalLocations;
    private int enemiesLeft;
    private String message;
    private String alert;
    private Status status;
}
