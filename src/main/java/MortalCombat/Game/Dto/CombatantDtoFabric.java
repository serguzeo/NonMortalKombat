package MortalCombat.Game.Dto;

import MortalCombat.Game.Combatant.Combatant;

public class CombatantDtoFabric {

    public static CombatantDto createCombatantDto(Combatant combatant) {
        CombatantDto dto = new CombatantDto();
        dto.setName(combatant.getName());
        dto.setIconPath(combatant.getIconPath());
        dto.setHealth(combatant.getHP());
        dto.setMaxHealth(combatant.getMaxHP());
        dto.setLevel(combatant.getLevel());
        dto.setDamage(combatant.getDamage());
        dto.setStunned(combatant.isStunned());
        dto.setWeakenFor(combatant.getWeakenFor());
        return dto;
    }
}
