package edu.eci.dosw.bowling;

public class Frame {
    private final java.util.List<Integer> individualRolls = new java.util.ArrayList<>();
    private int pinsKnockedDown = 0;
    private int rolls = 0;
    private boolean isTenthFrame = false;

    // Default constructor for standard frames
    public Frame() {}

    // Constructor to specify if it is the 10th frame
    public Frame(boolean isTenthFrame) {
        this.isTenthFrame = isTenthFrame;
    }

    public void addRoll(int pins) {
        if (!isTenthFrame && pinsKnockedDown + pins > 10) {
            throw new IllegalArgumentException("The sum of two rolls in a frame cannot exceed 10 pins");
        }
        pinsKnockedDown += pins;
        rolls++;
        individualRolls.add(pins); // Guardar el tiro
    }

    public FrameType getType() {
        if (rolls == 1 && pinsKnockedDown == 10) {
            return FrameType.STRIKE;
        }
        if (rolls == 2 && pinsKnockedDown == 10) {
            return FrameType.SPARE;
        }
        return FrameType.NORMAL;
    }

    public boolean isFull() {
        if (isTenthFrame) {
            if (rolls == 3) return true;
            if (rolls == 2 && pinsKnockedDown < 10) return true; // Ends at 2 if no strike or spare
            return false;
        }
        return rolls == 2 || getType() == FrameType.STRIKE;
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