package MortalCombat.Game.GameState;

import lombok.Getter;

import static MortalCombat.Game.Constants.*;

@Getter
public enum Status {
    LOSE(LOSE_MESSAGE),
    ALERT(""),
    LEVEL_UP(""),
    JUST_VICTORY(JUST_VICTORY_MESSAGE),
    TOP_VICTORY(TOP_VICTORY_MESSAGE);

    private final String message;

    Status(String message) {
        this.message = message;
    }

}
