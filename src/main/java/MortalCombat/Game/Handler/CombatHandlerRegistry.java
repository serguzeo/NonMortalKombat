package MortalCombat.Game.Handler;

import MortalCombat.Game.GameState.StepState;

import java.util.HashMap;
import java.util.Map;

public class CombatHandlerRegistry {
    private final Map<String, CombatHandler> handlers = new HashMap<>();

    public CombatHandlerRegistry() {
        handlers.put("ATTACK-ATTACK", new AttackAttackHandler());
        handlers.put("ATTACK-DEFEND", new AttackDefendHandler());
        handlers.put("ATTACK-WEAKEN", new AttackWeakenHandler());
        handlers.put("ATTACK-REGENERATE", new AttackRegenerateHandler());
        handlers.put("DEFEND-ATTACK", new DefendAttackHandler());
        handlers.put("DEFEND-DEFEND", new DefendDefendHandler());
        handlers.put("DEFEND-WEAKEN", new DefendWeakenHandler());
        handlers.put("DEFEND-REGENERATE", new DefendRegenerateHandler());
        handlers.put("WEAKEN-ATTACK", new WeakenAttackHandler());
        handlers.put("WEAKEN-DEFEND", new WeakenDefendHandler());
        handlers.put("WEAKEN-WEAKEN", new WeakenWeakenHandler());
        handlers.put("WEAKEN-REGENERATE", new WeakenRegenerateHandler());
        handlers.put("REGENERATE-ATTACK", new RegenerateAttackHandler());
        handlers.put("REGENERATE-DEFEND", new RegenerateDefendHandler());
        handlers.put("REGENERATE-WEAKEN", new RegenerateWeakenHandler());
    }

    public CombatHandler getHandler(StepState stepState) {
        String key = stepState.getCombination();
        return handlers.getOrDefault(key, new DefaultCombatHandler());
    }
}
