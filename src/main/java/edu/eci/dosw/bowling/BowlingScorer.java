package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(java.util.List<Frame> frames) {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            score += frame.getPinsKnockedDown();

            if (frame.getType() == FrameType.SPARE && i + 1 < frames.size()) {
                score += frames.get(i + 1).getFirstRoll();
            } else if (frame.getType() == FrameType.STRIKE && i + 1 < frames.size()) {
                Frame nextFrame = frames.get(i + 1);

                // If the next frame is also a strike, we need the first roll of the frame after that
                if (nextFrame.getType() == FrameType.STRIKE && i + 2 < frames.size()) {
                    score += nextFrame.getFirstRoll() + frames.get(i + 2).getFirstRoll();
                } else {
                    // Otherwise, the next frame has at least two rolls we can use
                    score += nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
                }
            }
        }
        return score;
    }
}