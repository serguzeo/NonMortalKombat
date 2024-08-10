package MortalCombat.Game.Handler;

import MortalCombat.Game.StepState;

import java.util.HashMap;
import java.util.Map;

public class CombatHandlerRegistry {
    private final Map<String, CombatHandler> handlers = new HashMap<>();

    public CombatHandlerRegistry() {
        handlers.put("ATTACK-ATTACK", new AttackAttackHandler());
        handlers.put("ATTACK-DEFEND", new AttackDefendHandler());
        handlers.put("ATTACK-WEAKEN", new AttackWeakenHandler());
        handlers.put("DEFEND-ATTACK", new DefendAttackHandler());
        handlers.put("DEFEND-DEFEND", new DefendDefendHandler());
        handlers.put("DEFEND-WEAKEN", new DefendWeakenHandler());
        handlers.put("WEAKEN-ATTACK", new DefendDefendHandler());
        handlers.put("WEAKEN-DEFEND", new DefendDefendHandler());
        handlers.put("WEAKEN-WEAKEN", new DefendWeakenHandler());
    }

    public CombatHandler getHandler(StepState stepState) {
        String key = stepState.getCombination();
        return handlers.getOrDefault(key, new DefaultCombatHandler());
    }
}
