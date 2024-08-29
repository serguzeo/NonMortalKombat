package MortalCombat.Game.GUI;

import MortalCombat.Game.Dto.ItemDto;
import MortalCombat.Game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

/**
 * Класс ItemBagDialog представляет диалоговое окно инвентаря игрока,
 * где отображаются все доступные предметы и их количество. Игрок может использовать предметы из этого окна.
 */
public class ItemBagDialog extends JDialog {
    private JPanel contentPane;
    private JPanel itemPanel;

    private final Game game;
    private final GameWindow gameWindow;
    private final ImageManager imageManager = new ImageManager();

    /**
     * Конструктор ItemBagDialog инициализирует диалоговое окно для отображения инвентаря.
     *
     * @param gameWindow окно игры, вызвавшее этот диалог.
     * @param game       текущий объект игры.
     */
    public ItemBagDialog(GameWindow gameWindow, Game game) {
        setContentPane(contentPane);
        setModal(true);

        this.game = game;
        this.gameWindow = gameWindow;
        fillItemScrollPanel();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Метод fillItemScrollPanel() заполняет панель с предметами, отображая каждый предмет в инвентаре.
     */
    private void fillItemScrollPanel() {
        List<ItemDto> itemDtoList = game.getItemBag();
        itemDtoList = itemDtoList.stream().sorted(Comparator.comparingInt(ItemDto::getId)).toList();

        // Добавим начальный отступ
        itemPanel.add(Box.createRigidArea(new Dimension(20, 10)));

        for (ItemDto itemDto : itemDtoList) {
            itemPanel.add(createItemDetailPanel(itemDto));

            // Добавим промежуток между панелями
            itemPanel.add(Box.createRigidArea(new Dimension(20, 10)));
        }

        // Обновим интерфейс
        itemPanel.revalidate();
        itemPanel.repaint();
    }

    /**
     * Метод createItemDetailPanel(ItemDto itemDto) создает панель, содержащую информацию о предмете.
     *
     * @param itemDto объект ItemDto, содержащий информацию о предмете.
     * @return панель с деталями предмета.
     */
    private JPanel createItemDetailPanel(ItemDto itemDto) {
        JPanel itemDetailPanel = new JPanel();
        itemDetailPanel.setLayout(new BoxLayout(itemDetailPanel, BoxLayout.Y_AXIS));

        itemDetailPanel.add(createCenteredLabel(itemDto.getName()));
        itemDetailPanel.add(Box.createRigidArea(new Dimension(10, 5)));
        itemDetailPanel.add(createCenteredIcon(itemDto.getIconPath()));
        itemDetailPanel.add(Box.createRigidArea(new Dimension(10, 8)));
        itemDetailPanel.add(createCenteredCountLabel(itemDto.getCount()));
        itemDetailPanel.add(Box.createRigidArea(new Dimension(10, 8)));
        itemDetailPanel.add(createCenteredButton(itemDto));

        return itemDetailPanel;
    }

    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel createCenteredIcon(String iconPath) {
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageManager.resizeIcon(imageManager.getIcon(iconPath), 100, 100));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return iconLabel;
    }

    private JLabel createCenteredCountLabel(int count) {
        JLabel countLabel = new JLabel("( " + count + " )");
        Color color = getColorForCount(count);
        countLabel.setForeground(color);
        countLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return countLabel;
    }

    private Color getColorForCount(int count) {
        if (count == 0) {
            return Color.RED;
        } else if (count == 1) {
            return Color.ORANGE;
        } else {
            return Color.GREEN;
        }
    }

    private JButton createCenteredButton(ItemDto itemDto) {
        JButton button = new JButton("Использовать");
        button.addActionListener(e -> {
            gameWindow.renderWindow(game.processItemUsage(itemDto.getId()));
            dispose();
            ItemBagDialog itemBagDialog = new ItemBagDialog(gameWindow, game);
            itemBagDialog.pack();
            itemBagDialog.setLocationRelativeTo(null);
            itemBagDialog.setVisible(true);
        });
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setEnabled(itemDto.getCount() != 0 && itemDto.isActive());
        return button;
    }
}
