package MortalCombat.Game.Dto;

import lombok.Data;

@Data
public class GameMessageDto {
    private GameInfoDto gameInfo;
    private CombatantDto player;
    private CombatantDto enemy;
}
