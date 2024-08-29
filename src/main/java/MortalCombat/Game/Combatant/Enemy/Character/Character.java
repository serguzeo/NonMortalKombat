package MortalCombat.Game.Combatant.Enemy.Character;

/**
 * Интерфейс, представляющий персонажа врага.
 * Каждый класс, реализующий этот интерфейс, должен предоставить информацию о названии и иконке персонажа.
 */
public interface Character {
    String getName();
    String getIconPath();
}
