package MortalCombat.Game.Rating;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Rating {
    private String nickname;
    private int score;
    private LocalDate date;

    public Rating(String nickname, int score, LocalDate date) {
        this.nickname = nickname;
        this.score = score;
        this.date = date;
    }
}
