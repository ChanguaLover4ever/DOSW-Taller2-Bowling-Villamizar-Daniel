package edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingScorerTest {

    private void rollMany(BowlingGame game, int times, int pins) {
        for (int i = 0; i < times; i++) {
            game.roll(pins);
        }
    }

    @Test
    void shouldReturnZero_whenAllRollsAreZero() {
        // Arrange
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 0);
        BowlingScorer scorer = new BowlingScorer();

        // Act
        int score = scorer.calculate(game.getFrames());

        // Assert
        assertEquals(0, score, "A game with all gutter balls should score 0");
    }

    @Test
    void shouldReturnSumOfPins_whenNoStrikesOrSparesAreRolled() {
        // Arrange
        BowlingGame game = new BowlingGame();
        rollMany(game, 20, 1);
        BowlingScorer scorer = new BowlingScorer();

        // Act
        int score = scorer.calculate(game.getFrames());

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                20,
                score,
                "A game with 1 pin per roll should score 20"
        );
    }

    @Test
    void shouldAddNextRollBonus_whenSpareIsRolled() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(5);
        game.roll(5); // SPARE
        game.roll(3); // Next roll adds bonus to frame 1, and counts as 3 for frame 2
        rollMany(game, 17, 0);

        BowlingScorer scorer = new BowlingScorer();
        int score = scorer.calculate(game.getFrames());

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                16,
                score,
                "A spare should add the next roll's pins as a bonus"
        );
    }

    @Test
    void shouldAddNextTwoRollsBonus_whenStrikeIsRolled() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(10); // STRIKE
        game.roll(4);
        game.roll(3);
        rollMany(game, 16, 0);

        BowlingScorer scorer = new BowlingScorer();
        int score = scorer.calculate(game.getFrames());

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                24,
                score,
                "A strike should add the next two rolls as a bonus"
        );
    }
}