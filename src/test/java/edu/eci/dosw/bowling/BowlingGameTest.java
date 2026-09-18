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
}