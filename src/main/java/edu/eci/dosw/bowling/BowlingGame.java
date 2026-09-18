package edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene exactamente 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    /** Registra pinos derribados. Lanza IllegalArgumentException si pines < 0 o > 10.
     *  Lanza IllegalStateException si el juego ya termino. */
    public void roll(int pins) {
        if (frames.size() == 10 && frames.get(9).isFull()) {
            throw new IllegalStateException("Cannot roll, the game is complete");
        }

        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("The number of pins is not valid");
        }

        // Create a new frame if the list is empty or the last frame is full
        if (frames.isEmpty() || frames.get(frames.size() - 1).isFull()) {
            frames.add(new Frame());
        }

        // Add the roll to the current frame
        frames.get(frames.size() - 1).addRoll(pins);
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        // TODO: implementar con TDD
        return 0;
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        // TODO: implementar con TDD
        return false;
    }

    public List<Frame> getFrames() { return List.copyOf(frames); }
}