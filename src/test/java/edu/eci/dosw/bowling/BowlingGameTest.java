package edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameTest {

    @Test
    void shouldRegisterZeroPinsAndNotThrowException_whenRollingZero() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(0);

        // Assert
        assertEquals(1, game.getFrames().size(), "There should be exactly 1 frame registered");
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRollingNegativePins() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(-1),
                "Rolling a negative number of pins should throw IllegalArgumentException"
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRollingMoreThanTenPins() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(11),
                "Rolling more than 10 pins in a single roll should throw IllegalArgumentException"
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTwoRollsInAFrameExceedTenPins() {
        // Arrange
        BowlingGame game = new BowlingGame();
        game.roll(7);

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(6),
                "The sum of two rolls in a single frame cannot exceed 10 pins"
        );
    }

    @Test
    void shouldThrowIllegalStateException_whenRollingAfterGameIsComplete() {
        // Arrange
        BowlingGame game = new BowlingGame();
        // Roll 20 times to complete 10 normal frames without strikes or spares
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        // Act & Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalStateException.class,
                () -> game.roll(5),
                "Rolling after 10 frames are complete should throw IllegalStateException"
        );
    }

    @Test
    void shouldMarkFrameAsStrike_whenRollingTenPins() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        game.roll(10);

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(
                FrameType.STRIKE,
                game.getFrames().get(0).getType(),
                "Frame should be marked as STRIKE when 10 pins are knocked down on the first roll"
        );
    }
}