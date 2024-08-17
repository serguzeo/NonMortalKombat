package MortalCombat.Game.Dto;

import lombok.Data;

@Data
public class ItemDto {
    private int id;
    private String name;
    private boolean isActive;
    private String iconPath;
    private int count;
}
