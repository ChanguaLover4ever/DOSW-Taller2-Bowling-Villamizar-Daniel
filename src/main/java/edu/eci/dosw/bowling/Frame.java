package edu.eci.dosw.bowling;

public class Frame {
    private final java.util.List<Integer> individualRolls = new java.util.ArrayList<>();
    private int pinsKnockedDown = 0;
    private int rolls = 0;
    private boolean isTenthFrame = false;

    public Frame() {}

    public Frame(boolean isTenthFrame) {
        this.isTenthFrame = isTenthFrame;
    }

    public void addRoll(int pins) {
        if (!isTenthFrame && pinsKnockedDown + pins > 10) {
            throw new IllegalArgumentException("The sum of two rolls in a frame cannot exceed 10 pins");
        }
        pinsKnockedDown += pins;
        rolls++;
        individualRolls.add(pins);
    }

    // Nuevos métodos para encapsular el estado
    public boolean isStrike() {
        return rolls == 1 && pinsKnockedDown == 10;
    }

    public boolean isSpare() {
        return rolls == 2 && pinsKnockedDown == 10;
    }

    // Refactorizado para usar los nuevos métodos booleanos
    public FrameType getType() {
        if (isStrike()) {
            return FrameType.STRIKE;
        }
        if (isSpare()) {
            return FrameType.SPARE;
        }
        return FrameType.NORMAL;
    }

    public boolean isFull() {
        if (isTenthFrame) {
            if (rolls == 3) return true;
            if (rolls == 2 && pinsKnockedDown < 10) return true;
            return false;
        }
        return rolls == 2 || isStrike();
    }

    public int getFirstRoll() {
        return individualRolls.isEmpty() ? 0 : individualRolls.get(0);
    }

    public int getSecondRoll() {
        return individualRolls.size() > 1 ? individualRolls.get(1) : 0;
    }

    public int getPinsKnockedDown() {
        return pinsKnockedDown;
    }
}