package MortalCombat.Game.Dto;

import lombok.Data;

@Data
public class GameMessageDto {
    private int enemyProgressBarValue;
    private String enemyProgressBarString;
    private String enemyDamage;
    private String enemyIconPath;
    private String enemyName;
    private String enemyLevel;

    private String score;
    private String XP;
    private String location;
    private String enemiesLeft;
    private String message;

    private int playerProgressBarValue;
    private String playerProgressBarString;
    private String playerDamage;
    private String playerIconPath;
    private String playerName;
    private String playerLevel;
}
