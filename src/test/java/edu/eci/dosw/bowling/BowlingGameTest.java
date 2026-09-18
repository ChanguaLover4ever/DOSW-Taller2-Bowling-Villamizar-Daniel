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
}