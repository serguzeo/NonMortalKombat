package MortalCombat.Game.Rating;

import lombok.Data;

import java.time.LocalDate;

/**
 * Структура данных для хранения информации о рейтинге игрока.
 */
@Data
public class Rating {
    private String nickname;
    private int score;
    private LocalDate date;

    /**
     * Конструктор для создания нового объекта Rating.
     *
     * @param nickname никнейм игрока.
     * @param score количество очков рейтинга.
     * @param date дата, когда рейтинг был зарегистрирован.
     */
    public Rating(String nickname, int score, LocalDate date) {
        this.nickname = nickname;
        this.score = score;
        this.date = date;
    }
}
