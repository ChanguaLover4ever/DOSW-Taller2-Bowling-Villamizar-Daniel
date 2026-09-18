package edu.eci.dosw.bowling;

public class Frame {
    private int pinsKnockedDown = 0;
    private int rolls = 0;

    public void addRoll(int pins) {
        if (pinsKnockedDown + pins > 10) {
            throw new IllegalArgumentException("The sum of two rolls in a frame cannot exceed 10 pins");
        }
        pinsKnockedDown += pins;
        rolls++;
    }

    public boolean isFull() {
        return rolls == 2; // For now, a frame is full after 2 rolls
    }

    public FrameType getType() {
        return FrameType.NORMAL; // Hardcoded to force a failure
    }
}