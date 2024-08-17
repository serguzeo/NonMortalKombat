package MortalCombat.Game.Dto;

import MortalCombat.Game.Item.IItem;

public class ItemDtoFabric {
    public static ItemDto createItemDto(IItem item, int count) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getItemId());
        itemDto.setName(item.getName());
        itemDto.setActive(item.isActive());
        itemDto.setIconPath(item.getIconPath());
        itemDto.setCount(count);
        return itemDto;
    }
}
