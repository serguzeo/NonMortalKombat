package MortalCombat.Game.GameState;

import lombok.Getter;

import static MortalCombat.Game.Constants.JUST_VICTORY_MESSAGE;
import static MortalCombat.Game.Constants.LOSE_MESSAGE;

@Getter
public enum Status {
    LOSE(LOSE_MESSAGE),
    ALERT(""),
    LEVEL_UP(""),
    JUST_VICTORY(JUST_VICTORY_MESSAGE),
    TOP_VICTORY("");

    private final String message;

    Status(String message) {
        this.message = message;
    }

}
