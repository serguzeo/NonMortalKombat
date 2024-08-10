package MortalCombat.Game;

import MortalCombat.Game.Combatant.Combatant;

public class Game implements IGame {
    private final Player player;
    private Combatant combatant;

    private float currentRound;

    public Game(int locationsNumber, Player player) {
        this.player = player;
    }

    private Combatant getRandomCombatant() {
        return null;
    }
}
