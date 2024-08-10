package MortalCombat.Game.Combatant;

import java.util.List;
import java.util.Queue;

public class Player implements Combatant {
    private final Level level;

    private Queue<CombatantAction> strategy;

    public Player(Level level) {
        this.level = level;
    }

    public void increaseLevel() {
        level.increaseLevel();
    }


    @Override
    public void doActionWith(CombatantAction combatantAction, Combatant opponent) {

    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public String getName() {
        return "";
    }
}
