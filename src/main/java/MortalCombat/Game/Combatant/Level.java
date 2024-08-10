package MortalCombat.Game.Combatant;

import lombok.Getter;

public class Level {
    @Getter
    private int level;

    public Level(int level) {
        this.level = level;
    }

    public void increaseLevel() {
        level += 1;
    }
}
