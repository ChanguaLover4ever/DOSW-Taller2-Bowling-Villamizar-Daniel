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

    @Test
    void shouldAddBonusFromTwoDifferentFrames_whenConsecutiveStrikesAreRolled() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(10); // STRIKE (Frame 1)
        game.roll(10); // STRIKE (Frame 2)
        game.roll(3);  // Frame 3, Roll 1
        game.roll(4);  // Frame 3, Roll 2
        rollMany(game, 14, 0); // 7 frames restantes (14 tiros en cero)

        BowlingScorer scorer = new BowlingScorer();
        int score = scorer.calculate(game.getFrames());

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                47,
                score,
                "Consecutive strikes should calculate the bonus across multiple frames"
        );
    }

    @Test
    void shouldScore150_whenAllSparesAndLastRollIs5() {
        // Arrange
        BowlingGame game = new BowlingGame();
        rollMany(game, 21, 5); // 20 rolls of 5 (10 spares) + 1 final bonus roll of 5
        BowlingScorer scorer = new BowlingScorer();

        // Act
        int score = scorer.calculate(game.getFrames());

        // Assert
        assertEquals(150, score, "A game with all spares and a final 5 should score 150");
    }

    @Test
    void shouldScore300_whenPerfectGameIsRolled() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        rollMany(game, 12, 10); // 12 consecutive strikes

        BowlingScorer scorer = new BowlingScorer();
        int score = scorer.calculate(game.getFrames());

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                300,
                score,
                "A perfect game with 12 strikes should score exactly 300"
        );
    }
}