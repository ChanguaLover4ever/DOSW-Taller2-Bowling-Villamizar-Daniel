package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        if (frames.size() < 10) {
            throw new IllegalStateException("Cannot score an incomplete game");
        }
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            score += frame.getPinsKnockedDown();

            if (frame.getType() == FrameType.STRIKE) {
                score += strikeBonus(i, frames);
            } else if (frame.getType() == FrameType.SPARE) {
                score += spareBonus(i, frames);
            }
        }
        return score;
    }

    private int spareBonus(int frameIndex, List<Frame> frames) {
        if (frameIndex + 1 < frames.size()) {
            return frames.get(frameIndex + 1).getFirstRoll();
        }
        return 0;
    }

    private int strikeBonus(int frameIndex, List<Frame> frames) {
        if (frameIndex + 1 >= frames.size()) {
            return 0;
        }

        Frame nextFrame = frames.get(frameIndex + 1);

        if (nextFrame.getType() == FrameType.STRIKE && frameIndex + 2 < frames.size()) {
            return nextFrame.getFirstRoll() + frames.get(frameIndex + 2).getFirstRoll();
        }

        return nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
    }
}