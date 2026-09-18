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
}