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
        if (isComplete()) {
            throw new IllegalStateException("Cannot roll, the game is complete");
        }

        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("The number of pins is not valid");
        }

        Frame currentFrame = getOrCreateCurrentFrame();
        currentFrame.addRoll(pins);
    }

    private Frame getOrCreateCurrentFrame() {
        if (frames.isEmpty() || frames.get(frames.size() - 1).isFull()) {
            frames.add(new Frame());
        }
        return frames.get(frames.size() - 1);
    }

    /** Puntaje total. Lanza IllegalStateException si el juego no esta completo. */
    public int score() {
        // TODO: implementar con TDD
        return 0;
    }

    /** true cuando los 10 frames han sido completados. */
    public boolean isComplete() {
        return frames.size() == 10 && frames.get(9).isFull();
    }

    public List<Frame> getFrames() { return List.copyOf(frames); }
}